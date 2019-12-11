package optics

import arrow.core.Option
import arrow.core.NonEmptyList
import arrow.core.k
import optics.domain.*
import optics.domain.PersonOnFlight.Passenger
import optics.domain.PersonOnFlight.CrewMember

object repository {

    val passenger1 =
            Passenger(id = 1, name = "bob",
                    email = "bob@bob.com",
                    address = Address(city = "Belfast",
                            street = Street(1, "Antrim Rd")),
                    phone = Option(12345678))
    val passenger2 =
            Passenger(id = 2, name = "Jane",
                    email = "Jane@jane.com",
                    address = Address(city = "Belfast",
                            street = Street(5, "Lisburn Rd")))

    val passenger3 =
            Passenger(id = 3, name = "Derek",
                    email = "Derek@derek.com",
                    address = Address(city = "Belfast",
                            street = Street(5, "Park Ave")))

    val crewMember1 = CrewMember(employeeid = 4823, name = "Michael", title = "Steward")
    val crewMember2 = CrewMember(employeeid = 4825, name = "Kerry", title = "Stewardess")
    val crewMember3 = CrewMember(employeeid = 4827, name = "Ruth", title = "Captain")
    val crewMember4 = CrewMember(employeeid = 4829, name = "John", title = "Captain")

    val flight10567  = Flight(10567, "Belfast", "London",
            Option(NonEmptyList(crewMember1, crewMember3, passenger1, passenger2, passenger3)))
    val flight10023  = Flight(10023, "Dublin", "Paris",
            Option(NonEmptyList(crewMember2, passenger2, crewMember4, passenger3)))
    val flight10011 =  Flight(10011, "Berlin", "Rome",
            Option(NonEmptyList(crewMember2, crewMember4, passenger3)))

    val flights = Flights(listOf(flight10011, flight10023, flight10567).k())

}


