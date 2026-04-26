package com.airbnb.booking.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookingRequestDTO {

	@NotNull(message = "Property id is required")
	private Long propertyId;

	@NotNull(message = "Check-in date is required")
	@FutureOrPresent(message = "Check-in date must be today or in the future")
	private LocalDate checkIn;

	@NotNull(message = "Check-out date is required")
	private LocalDate checkOut;

	@Min(value = 1, message = "At least one guest is required")
	private int guests;

	// ✅ GETTERS & SETTERS

	public Long getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}

	public int getGuests() {
		return guests;
	}

	public void setGuests(int guests) {
		this.guests = guests;
	}
}
