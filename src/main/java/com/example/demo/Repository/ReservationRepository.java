package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
