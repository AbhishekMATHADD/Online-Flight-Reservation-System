package com.example.demo.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Payment;
import com.example.demo.Model.Reservation;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.ReservationRepository;

@Service
public class PaymentService {

	@Autowired
	private ReservationRepository resoRepo;

	@Autowired
	private PaymentRepository payRepo;

	public Payment savePayment(Payment payment, Reservation reservation) throws ParseException {
		Reservation reser = resoRepo.findById(reservation.getId()).get();
		Date date = new java.sql.Date(
				((java.util.Date) new SimpleDateFormat("dd-MM-yyyy").parse(payment.getExpiryDate())).getTime());
		payment.setExpiryDate(String.valueOf(payment));
		payment.setReservation(reservation);
		return payRepo.save(payment);
	}

	public Payment getPayment(Long id) {
		return payRepo.findById(id).get();

	}
}
