package com.example.demo.Model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String flightName;

	private String source;

	private String desination;

	private String estimatedTravelDuration;

	private Integer seatingCapacity;

	private String reservationType;

	private Integer reservationCapacity;

	public Flight() {
	}

	public Flight(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDesination() {
		return desination;
	}

	public void setDesination(String desination) {
		this.desination = desination;
	}

	public String getEstimatedTravelDuration() {
		return estimatedTravelDuration;
	}

	public void setEstimatedTravelDuration(String estimatedTravelDuration) {
		this.estimatedTravelDuration = estimatedTravelDuration;
	}

	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public Integer getReservationCapacity() {
		return reservationCapacity;
	}

	public void setReservationCapacity(Integer reservationCapacity) {
		this.reservationCapacity = reservationCapacity;
	}

}
