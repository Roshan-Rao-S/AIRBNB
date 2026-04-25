package com.airbnb.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airbnb.booking.dto.BookingRequestDTO;
import com.airbnb.booking.entity.Booking;
import com.airbnb.booking.service.BookingService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequestDTO dto,
            HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return ResponseEntity.ok(
                bookingService.createBooking(dto, email)
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getUserBookings(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        return ResponseEntity.ok(
                bookingService.getUserBookings(email)
        );
    }
}