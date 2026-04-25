package com.airbnb.booking.repository;

import org.springframework.data.repository.CrudRepository;
import com.airbnb.booking.entity.Booking;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findByUserEmail(String email);
}