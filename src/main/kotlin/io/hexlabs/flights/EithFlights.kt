package io.hexlabs.flights

import error.repository.flightManafests
import error.repository.flights
import error.repository.users
import arrow.core.*
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.fx

/**
 * Take null flights implementation and refactor to use Either
 */
object EithFlights {

    fun userByName(name: String): Either<FlightFailure, User> =
            Option.fromNullable(users.firstOrNull { it.name == name })
                    .toEither { NotFound("no user id for $name") }

    fun manifestsContainingUser(user: User): Either<FlightFailure, Nel<FlightManafest>> =
            Either.fx {
                val flightManafests = !attemptFetchManafests(user)
                !Nel.fromList(flightManafests).toEither { NotFound("no flight manafests found for user ${user.name}") }
            }

    fun attemptFetchManafests(user: User): Either<FlightFailure, List<FlightManafest>> =
            Either.catchAs({ FlightSystemException(it) }) {
                flightManafests.filter { fm -> fm.passengers.contains(user.id) }
            }


    fun flightsFromManifests(manifests: Nel<FlightManafest>): Either<FlightFailure, Nel<Flight>> =
            manifests.map { flightById(it.flightNo) }.sequence()

    fun flightById(flightNo: Int): Either<FlightFailure, Flight> =
            Option.fromNullable(flights.firstOrNull { it.flightNo == flightNo })
                    .toEither { NotFound("no flights for $flightNo") }

    fun userFlights(name: String): Either<FlightFailure, Nel<Flight>> = Either.fx {
                val user = !userByName(name)
                val manifests = !manifestsContainingUser(user)
                val eithFlights = manifests.map { flightById(it.flightNo) }
                !eithFlights.sequence()
            }

    fun userFlightsFlatMap(name: String): Either<FlightFailure, Nel<Flight>> =
            userByName(name).flatMap { user ->
                manifestsContainingUser(user).flatMap { manifests ->
                    manifests.map { flightById(it.flightNo) }.sequence()
                }
            }

    private fun <T> Nel<Either<FlightFailure, T>>.sequence(): Either<FlightFailure, Nel<T>> =
            this.sequence(Either.applicative()).fix()

    private fun <L, R> Either.Companion.catchAs(fe: (Throwable) -> L, f: () -> R): Either<L, R> =
            try {
                f().right()
            } catch (t: Throwable) {
                fe(t.nonFatalOrThrow()).left()
            }
}