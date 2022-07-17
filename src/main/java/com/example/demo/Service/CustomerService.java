package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Reservation;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.ReservationRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customRepo;

	@Autowired
	private ReservationRepository resoRepo;

	public Customer savePassenger(Customer customer, Reservation reservation) {
		Reservation reser = resoRepo.findById(reservation.getId()).get();
		customer.setReservation(reser);
		return customRepo.save(customer);
	}
}
