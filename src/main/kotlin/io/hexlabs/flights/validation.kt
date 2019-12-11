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

fun inRange(lower: Int, upper: Int, data: Int): Either<Failure, Int> = TODO()

fun validZip(data: String): Either<Failure, String> = TODO()

fun validCities(data: String): Either<Failure, String> = TODO()

//Validate and build an employee using bind
fun empEitherFromMonad(name: String, zipCode: String, city: String, salary: Int): Either<Failure, Employee> =
       TODO()

//Validate and build an employee using applicative map
fun empEitherFromApp(name: String, zipCode: String, city: String, salary: Int): Either<Failure, Employee> =
        TODO()

//Validate and build an employee using Validated
fun empValidatedFromApp(name: String, zipCode: String, city: String, salary: Int): Validated<Nel<Failure>, Employee> =
       TODO()
