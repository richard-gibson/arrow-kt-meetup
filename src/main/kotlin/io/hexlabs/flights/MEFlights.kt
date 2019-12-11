package error

import arrow.Kind
import error.repository.flightManafests
import error.repository.flights
import error.repository.users
import arrow.core.*
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.typeclasses.MonadError
import io.hexlabs.flights.EmptyReponseException
import io.hexlabs.flights.Flight
import io.hexlabs.flights.FlightFailure
import io.hexlabs.flights.FlightManafest
import io.hexlabs.flights.NotFound
import io.hexlabs.flights.User

/**
 * Take flights implementation and refactor to use MonadError
 */
class MEFlights<F, E>(val ME: MonadError<F, E>, val FunctK: FunctionK<ForOption, F>) :
        MonadError<F, E> by ME {

    fun <T> Nel<Kind<F, T>>.sequence(): Kind<F, Nel<T>> =
            this.sequence(ME)

    fun userByName(name: String): Kind<F, User> = TODO()

    fun manifestsContainingUser(user: User): Kind<F, Nel<FlightManafest>> = TODO()


    fun flightById(flightNo: Int): Kind<F, Flight> = TODO()

    fun flightsFromManifests(manifests: Nel<FlightManafest>): Kind<F, Nel<Flight>> = TODO()

    fun userFlights(name: String): Kind<F, Nel<Flight>> = TODO()

    fun userFlightsFM(name: String): Kind<F, Nel<Flight>> = TODO()
}

typealias EitherEmpty = EitherPartialOf<FlightFailure>

object OptionToEither : FunctionK<ForOption, EitherEmpty> {
    override fun <A> invoke(fa: Kind<ForOption, A>): Kind<EitherEmpty, A> =
            fa.fix().toEither { NotFound("field not found") }
}

object OptionIdentity : FunctionK<ForOption, ForOption> {
    override fun <A> invoke(fa: Kind<ForOption, A>): Kind<ForOption, A> = fa
}

object OptionToIO : FunctionK<ForOption, ForIO> {
    override fun <A> invoke(fa: Kind<ForOption, A>): Kind<ForIO, A> =
            fa.fix().fold({ IO.raiseError(EmptyReponseException("field not found")) }) {
                IO.just(it)
            }

}
