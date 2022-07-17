package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Flight;
import com.example.demo.Model.FlightSchedule;
import com.example.demo.Repository.FlightRepositoy;
import com.example.demo.Repository.FlightScheduleRepository;

@Service
public class FlightScheduleService {

	@Autowired
	private FlightRepositoy flightRepo;

	@Autowired
	private FlightScheduleRepository scheduleRepo;

	public void saveFlightSchedule(FlightSchedule flightSchedule, Flight flight) {
		Flight f = flightRepo.findById(flight.getId()).get();
		flightSchedule.setFlight(flight);
		scheduleRepo.save(flightSchedule);
	}

	public FlightSchedule getFlightScheduleByID(Long id) {
		FlightSchedule flightSchedule = scheduleRepo.findById(id).get();
		return flightSchedule;
	}

	public void deleteFlightSchedule(Long id) {
		scheduleRepo.deleteById(id);
	}

}
