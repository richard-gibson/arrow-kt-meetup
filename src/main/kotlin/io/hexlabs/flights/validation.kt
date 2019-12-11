package io.hexlabs.flights

import arrow.core.*
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.either.monadError.*
import arrow.core.extensions.fx
import arrow.core.extensions.nonemptylist.semigroup.semigroup
import arrow.core.extensions.validated.applicativeError.applicativeError
import error.repository.cities
import error.repository.zips

sealed class Failure
data class EmptyString(val fieldName: String) : Failure()
data class InvalidCity(val fieldName: String) : Failure()
data class ValueOutOfRange(val i: Int) : Failure()

data class Employee(val name: String, val zipCode: String, val city: String, val salary: Int)

fun <A, B> Either<A, B>.toValidatedNel(): ValidatedNel<A, B> =
        this.fold({ it.invalidNel() }, { it.valid() })

// Create validation functions for nonBlank, inRange, validZip, validCities
fun nonBlank(fieldName: String, data: String): Either<Failure, String> =
        data.right().ensure({ EmptyString("$fieldName cannot be blank") }, { it.isNotEmpty() })

fun inRange(lower: Int, upper: Int, data: Int): Either<Failure, Int> =
        data.right().ensure({ ValueOutOfRange(data) }, { it in lower..upper })

fun validZip(data: String): Either<Failure, String> =
        data.right().ensure({ InvalidCity(data) }, { it in zips })

fun validCities(data: String): Either<Failure, String> =
        data.right().ensure({ InvalidCity(data) }, { it in cities })

//Validate and build an employee using bind
fun empEitherFromMonad(name: String, zipCode: String, city: String, salary: Int): Either<Failure, Employee> =
        Either.fx {
            val n = !nonBlank("name", name)
            val z = !validZip(zipCode)
            val c = !validCities(city)
            val s = inRange(10, 20, salary).bind()
            Employee(n, z, c, s)
        }

//Validate and build an employee using applicative map
fun empEitherFromApp(name: String, zipCode: String, city: String, salary: Int): Either<Failure, Employee> =
        Either.applicative<Failure>().map(
                nonBlank("name", name),
                validZip(zipCode),
                validCities(city),
                inRange(10, 20, salary)) { (n, z, c, s) ->
            Employee(n, z, c, s)
        }.fix()

//Validate and build an employee using Validated
fun empValidatedFromApp(name: String, zipCode: String, city: String, salary: Int): Validated<Nel<Failure>, Employee> =
        Validated.applicativeError<Nel<Failure>>(NonEmptyList.semigroup()).map(
                nonBlank("name", name).toValidatedNel(),
                validZip(zipCode).toValidatedNel(),
                validCities(city).toValidatedNel(),
                inRange(10, 20, salary).toValidatedNel()) { (n, z, c, s) ->
            Employee(n, z, c, s)
        }.fix()
