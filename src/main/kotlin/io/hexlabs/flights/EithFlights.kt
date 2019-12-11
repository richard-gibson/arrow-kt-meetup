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

    fun userByName(name: String): Either<FlightFailure, User> = TODO()

    fun manifestsContainingUser(user: User): Either<FlightFailure, Nel<FlightManafest>> = TODO()

    fun attemptFetchManafests(user: User): Either<FlightFailure, List<FlightManafest>> = TODO()


    fun flightsFromManifests(manifests: Nel<FlightManafest>): Either<FlightFailure, Nel<Flight>> = TODO()

    fun flightById(flightNo: Int): Either<FlightFailure, Flight> = TODO()

    fun userFlights(name: String): Either<FlightFailure, Nel<Flight>> = TODO()

    fun userFlightsFlatMap(name: String): Either<FlightFailure, Nel<Flight>> = TODO()

    private fun <T> Nel<Either<FlightFailure, T>>.sequence(): Either<FlightFailure, Nel<T>> =
            this.sequence(Either.applicative()).fix()

    private fun <L, R> Either.Companion.catchAs(fe: (Throwable) -> L, f: () -> R): Either<L, R> =
            try {
                f().right()
            } catch (t: Throwable) {
                fe(t.nonFatalOrThrow()).left()
            }
}