package com.example.demo.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.Flight;
import com.example.demo.Repository.FlightRepositoy;

@Service
public class FlightService {

	@Autowired
	private FlightRepositoy flightRepo;

	public List<Flight> listAll() {
		return flightRepo.findAll();
	}

	public void deleteFlightById(Long id) {
		flightRepo.deleteById(id);
	}

	public Flight getFlightDetails(Long id) {
		return flightRepo.findById(id).get();
	}

	public void save(Flight flight) throws ParseException {
		flight.setEstimatedTravelDuration(String.valueOf(flight.getEstimatedTravelDuration()));
		flightRepo.save(flight);
	}

}
