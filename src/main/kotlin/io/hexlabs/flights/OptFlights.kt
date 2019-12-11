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

    fun userByName(name: String): Option<User> = TODO()

    fun manifestsContainingUser(user: User): Option<Nel<FlightManafest>> = TODO()

    fun flightById(flightNo: Int): Option<Flight> = TODO()

    fun userFlightsFlatMap(name: String): Option<Nel<Flight>> = TODO()

    fun userFlights(name: String): Option<Nel<Flight>> = TODO()


    fun <T> Nel<Option<T>>.sequence(): Option<Nel<T>> =
            this.sequence(Option.applicative()).fix()
}