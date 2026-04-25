package com.airbnb.booking.service;

import com.airbnb.booking.dto.BookingRequestDTO;
import com.airbnb.booking.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingRequestDTO dto, String email);

    Booking cancelBooking(Long id);

    List<Booking> getUserBookings(String email);
}