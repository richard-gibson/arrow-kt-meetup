package optics

import arrow.core.ListK
import arrow.core.NonEmptyList
import arrow.optics.Lens
import arrow.optics.Optional
import arrow.optics.Prism
import arrow.optics.Traversal
import arrow.optics.dsl.every
import arrow.optics.dsl.index
import arrow.optics.extensions.listk.each.each
import arrow.optics.extensions.listk.index.index
import arrow.optics.extensions.nonemptylist.index.index
import optics.domain.*
import optics.domain.PersonOnFlight.Passenger
import optics.repository.flights


//create a Lens to modify a Street name
val modifyStreetName: Lens<Street, String> = Street.name

//create a Lens to modify a Street name
val modifyAddressStreet: Lens<Address, Street> = TODO()

//create a Lens to modify a Passenger address
val modifyPassengerAddress: Lens<Passenger, Address> = TODO()

//create a Prism that will modify a PersonOnFlight if they are a passenger
val modifyPassenger: Prism<PersonOnFlight, Passenger> = TODO()

//create a Optional that will modify the PersonOnFlight at location 2
val modifyPersonOnFlightLoc2: Optional<Flight, PersonOnFlight> = TODO()

//create an Traversal that will modify the Flight at location 1: Hint use Index
val modifyFlightLoc1: Optional<Flights, Flight> = TODO()


//create an Traversal that will modify the Flight at location 1
val modifyAllFlights: Traversal<Flights, Flight> = TODO()


/**
 * createa a Traversal that will uppercase the street name of the Person on board at Loc 2
 * if they are a passenger for all flights
 */

val modifyAllStreetNamesForFlights: Traversal<Flights, String> =
        modifyAllFlights compose modifyPersonOnFlightLoc2 compose
                modifyPassenger compose modifyPassengerAddress compose
                modifyAddressStreet compose modifyStreetName


/**
 * create a a Traversal that will uppercase the street name of the Person on board at Loc 2
 * if they are a passenger for all flights without compose
 */
val modifyAllStreetNamesForFlights2: Traversal<Flights, String>  =
        Flights.flights.every(ListK.each())
                .manifest.index(NonEmptyList.index(), 2)
                .passenger
                .address
                .street
                .name



