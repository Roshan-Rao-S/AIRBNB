package com.airbnb.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.airbnb.booking.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserEmail(String email);
}
