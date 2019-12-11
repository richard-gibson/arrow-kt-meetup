package optics

import arrow.optics.Lens
import optics.domain.Company
import optics.domain.Employee
import optics.domain.address
import optics.domain.company
import optics.domain.name
import optics.domain.street
import optics.repository

fun main(args: Array<String>) {

    val street = repository.passenger2.address.street
    val newStreet = modifyStreetName.modify(street){"Foo st"}
    println(newStreet)

    val address = repository.passenger2.address
    val newAddress = modifyAddressStreet.modify(address){newStreet}
    println(newAddress)

    val passenger3 = repository.passenger3
    val newPassenger3 = modifyPassengerAddress.modify(passenger3){newAddress}
    println(newPassenger3)


    val crewMember = repository.crewMember1
    println(modifyPassenger.getOption(crewMember))
    println(modifyPassenger.modifyOption(crewMember){newPassenger3})

    println(modifyPassenger.getOption(passenger3))
    println(modifyPassenger.modifyOption(passenger3){newPassenger3})


    val flight = repository.flight10011
    val newFlight = modifyPersonOnFlightLoc2.modify(flight){newPassenger3}
    println(modifyPersonOnFlightLoc2.modify(flight){crewMember})
    println(newFlight)

    val flights = repository.flights
    //cheating, could compose with
    println(modifyFlightLoc1.modify(flights){newFlight})

    println(modifyAllFlights.modify(flights){newFlight})


    val modifiedFlights1 = modifyAllStreetNamesForFlights.modify(flights){"FOO ST"}
    val modifiedFlights2 = modifyAllStreetNamesForFlights2.modify(flights){"FOO ST"}
    println(modifiedFlights1)
    println(modifiedFlights2 == modifiedFlights1)
    val employee: Employee = Employee("foo", Company("e", passenger3.address))

    val streetNameLens: Lens<Employee, String> = Employee.company.address.street.name
    streetNameLens.modify(employee) {it.toUpperCase()}
}
