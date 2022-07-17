package com.example.demo.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Flight;
import com.example.demo.Model.Reservation;
import com.example.demo.Model.User;
import com.example.demo.Repository.FlightRepositoy;
import com.example.demo.Repository.ReservationRepository;
import com.example.demo.Repository.UserRepositoy;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository resoRepo;

	@Autowired
	private FlightRepositoy flightRepo;

	public Reservation saveReservation(Reservation reservation, Flight flight) {
		Flight f = flightRepo.findById(flight.getId()).get();
		reservation.setFlight(f);
		return resoRepo.save(reservation);
	}

	public Reservation getReservationID(Long id) {
		return resoRepo.findById(id).get();
	}

}