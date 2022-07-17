package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Model.Flight;

import com.example.demo.Repository.FlightRepositoy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class FlightRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FlightRepositoy flightRepo;

	@Test
	public void adfFligthtTest() throws ParseException {
		Flight flight = new Flight();
		flight.setFlightName("Indigo");
		flight.setSource("Assam");
		flight.setDesination("Huballi");
		flight.setReservationCapacity(100);
		flight.setReservationType("Domastic");
		flight.setSeatingCapacity(150);

		// Time time = Time.valueOf("12:13");
		String str = "12:20 pm";
		flight.setEstimatedTravelDuration(String.valueOf(str));
		Flight savedFlight = flightRepo.save(flight);

		Flight existFlight = entityManager.find(Flight.class, savedFlight.getId());

		assertThat(flight.getId()).isEqualTo(existFlight.getId());

	}

	/*
	 * @Test public void deleteFligthtTest() { flightRepo.deleteById((long)1); }
	 */

}
