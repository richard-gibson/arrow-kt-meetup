package error

import arrow.core.Either
import arrow.core.Option
import arrow.core.extensions.either.monadError.monadError
import arrow.core.extensions.option.monadError.monadError
import io.hexlabs.flights.empEitherFromApp
import io.hexlabs.flights.empEitherFromMonad
import io.hexlabs.flights.empValidatedFromApp
import io.hexlabs.flights.EithFlights
import io.hexlabs.flights.FlightFailure


object OptFlightOps {
    @JvmStatic
    fun main() {
        println()
        println(OptFlights.userFlights("bob"))
        println(OptFlights.userFlights("Brian"))

        println()
        println(OptFlights.userFlightsFlatMap("bob"))
        println(OptFlights.userFlightsFlatMap("Brian"))
    }
}

object EitherFlightOps {
    @JvmStatic
    fun main(args: Array<String>) {
        println()
        println(EithFlights.userFlights("bob"))
        println(EithFlights.userFlights("Brian"))

        println()
        println(EithFlights.userFlightsFlatMap("bob"))
        println(EithFlights.userFlightsFlatMap("Brian"))
    }
}


object Validation {

    @JvmStatic
    fun main(a: Array<String>) {
        println(empEitherFromApp("foo", "00111", "Dublin", 15))
        println(empEitherFromApp("", "00", "Washington", 17500))
        println(empEitherFromApp("foo", "00", "Washington", 17500))
        println()

        println()
        println(empEitherFromMonad("foo", "00111", "Dublin", 15))
        println(empEitherFromMonad("", "00", "Washington", 17500))
        println(empEitherFromMonad("foo", "00", "Washington", 17500))
        println()

        println()
        println(empValidatedFromApp("foo", "00111", "Dublin", 15))
        println(empValidatedFromApp("", "00", "Washington", 17500))
        println(empValidatedFromApp("foo", "00", "Washington", 17500))
    }

}

object MEFlightOps {
    @JvmStatic
    fun main(args: Array<String>) {

        val optModule = MEFlights(Option.monadError(), OptionIdentity )
        val eithModule = MEFlights(Either.monadError<FlightFailure>(), OptionToEither)

        println(optModule.userFlights("bob"))
        println(eithModule.userFlights("bob"))

        println(optModule.userFlights("Brian"))
        println(eithModule.userFlights("Brian"))

    }
}