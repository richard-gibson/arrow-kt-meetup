package error

import arrow.core.k
import arrow.core.some
import io.hexlabs.flights.Address
import io.hexlabs.flights.Flight
import io.hexlabs.flights.FlightManafest
import io.hexlabs.flights.Street
import io.hexlabs.flights.User

object repository {
    val flights = listOf(
            Flight(10567, "Belfast", "London"),
            Flight(10023, "Dublin", "Paris"),
            Flight(10011, "Berlin", "Rome")
    ).k()

    val users = listOf(
            User(id = 1, name = "bob",
                    email = "bob@bob.com",
                    address = Address(city = "Belfast",
                            street = Street(1, "Antrim Rd")),
                    phone = 12345678.some()),
            User(id = 2, name = "Jane",
                    email = "Jane@jane.com",
                    address = Address(city = "Belfast",
                            street = Street(5, "Antrim Rd")))
    ).k()

    val flightManafests = listOf(
            FlightManafest(10567, listOf(1, 2)),
            FlightManafest(10023, listOf(1))
    ).k()



    val zips = listOf("10002", "10005", "10022")
    val cities = listOf("Dublin", "London", "Madrid")
}


