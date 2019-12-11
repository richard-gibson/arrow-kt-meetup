package optics.domain

import arrow.core.ListK
import arrow.core.NonEmptyList
import arrow.core.Option
import arrow.optics.optics

/*@optics
data class Street(val number: Int, val name: String) {
    companion object
}
@optics
data class Address(val city: String, val street: Street) {
    companion object
}*/
@optics
data class Flights(val flights: ListK<Flight>) {
    companion object
}

@optics
data class Flight(val flightNo: Int, val destination: String, val departure: String,
                  val manifest: Option<NonEmptyList<PersonOnFlight>>) {
    companion object
}
@optics
sealed class PersonOnFlight {
    companion object{}
    @optics
    data class Passenger(val id: Int, val name: String, val email: String, val address: Address, val phone: Option<Int> =
            Option.empty()) : PersonOnFlight() {
        companion object
    }
    @optics
    data class CrewMember(val employeeid: Int, val name: String, val title: String) : PersonOnFlight() {
        companion object
    }
}

@optics
data class Street(val number: Int, val name: String) {
    companion object
}
@optics
data class Address(val city: String, val street: Street) {
    companion object
}
@optics
data class Company(val name: String, val address: Address) {
    companion object
}
@optics
data class Employee(val name: String, val company: Company) {
    companion object
}