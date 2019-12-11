package error

import error.repository.flightManafests
import error.repository.flights
import error.repository.users
import arrow.core.*
import arrow.core.extensions.fx
import arrow.core.extensions.option.applicative.applicative
import io.hexlabs.flights.Flight
import io.hexlabs.flights.FlightManafest
import io.hexlabs.flights.User

/**
 * Take null flights implementation and refactor to use Option
 */
object OptFlights {

    fun userByName(name: String): Option<User> =
            Option.fromNullable(users.firstOrNull { it.name == name })

    fun manifestsContainingUser(user: User): Option<Nel<FlightManafest>> =
            Nel.fromList(flightManafests.filter { fm -> fm.passengers.contains(user.id) })

    fun flightById(flightNo: Int): Option<Flight> =
            Option.fromNullable(flights.firstOrNull { it.flightNo == flightNo })

  fun userFlightsFlatMap(name:String): Option<Nel<Flight>> =
      try {
        userByName(name)
            .flatMap{ user -> manifestsContainingUser(user) }
            .flatMap { manifests -> manifests.map{ flightById(it.flightNo) }.sequence() }

      } catch (e: Exception) {
        println("Something went wrong ${e.message}")
        throw e
      }

    fun userFlights(name: String): Option<Nel<Flight>> =
            try {
                Option.fx {
                    val user = !userByName(name)
                    val manifests = !manifestsContainingUser(user)
                    val optFlights = manifests.map { flightById(it.flightNo) }
                    !optFlights.sequence()
                }


            } catch (e: Exception) {
                println("Something went wrong ${e.message}")
                throw e
            }

  fun <T> Nel<Option<T>>.sequence(): Option<Nel<T>> =
      this.sequence(Option.applicative()).fix()
}