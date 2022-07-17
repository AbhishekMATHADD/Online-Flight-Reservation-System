package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.Model.Flight;
import com.example.demo.Model.Reservation;
import com.example.demo.Model.User;
import com.example.demo.Repository.FlightRepositoy;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Repository.UserRepositoy;

import org.junit.jupiter.api.Test;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ReservationRepository resRepo;

	@Autowired
	private UserRepositoy userRepo;

	@Autowired
	private FlightRepositoy flightRepo;

	/*
	 * @Test public void addReservationFlight() throws ParseException { Reservation
	 * reso = new Reservation(); User user = userRepo.findById((long)1468342).get();
	 * System.out.println(user.getId()); Flight flight =
	 * flightRepo.findById((long)1001).get(); reso.setUser(user);
	 * reso.setNoOfSetas(2); String str = "21-06-2022"; Date date = new
	 * java.sql.Date( ((java.util.Date) new
	 * SimpleDateFormat("dd-MM-yyyy").parse(str)).getTime());
	 * reso.setDateOfJourney(str); reso.setReservationType("BusinessClass");
	 * reso.setFlight(flight);
	 * 
	 * Reservation savedReso = resRepo.save(reso);
	 * 
	 * Reservation existReso = entityManager.find(Reservation.class,
	 * savedReso.getId());
	 * 
	 * assertThat(reso.getId()).isEqualTo(existReso.getId());
	 * 
	 * }
	 */

	@Test
	public void addReservationFlight() {
		resRepo.deleteById((long) 12346);
	}

}
