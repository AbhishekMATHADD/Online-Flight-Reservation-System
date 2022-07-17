package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Flight;

public interface FlightRepositoy extends JpaRepository<Flight, Long> {

}
