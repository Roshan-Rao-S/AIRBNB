package com.airbnb.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.airbnb.booking.dto.BookingRequestDTO;
import com.airbnb.booking.entity.Booking;
import com.airbnb.booking.repository.BookingRepository;
import com.airbnb.calendar.dto.AvailabilityDTO;
import com.airbnb.calendar.service.AvailabilityService;
import com.airbnb.userservice.exception.BadRequestException;
import com.airbnb.userservice.exception.ResourceNotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository bookingRepository;
    private final AvailabilityService availabilityService;

    @Autowired
    public BookingServiceImpl(
            BookingRepository bookingRepository,
            AvailabilityService availabilityService
    ) {
        this.bookingRepository = bookingRepository;
        this.availabilityService = availabilityService;
    }

//    @Override
//    public Booking createBooking(BookingRequestDTO dto, String email) {
//
//        Booking booking = new Booking();
//
//        booking.setUserEmail(email);
//        booking.setPropertyId(dto.getPropertyId());
//        booking.setCheckIn(dto.getCheckIn());
//        booking.setCheckOut(dto.getCheckOut());
//        booking.setGuests(dto.getGuests());
//        booking.setStatus("CONFIRMED"); // Instant booking
//
//        return bookingRepository.save(booking);
//    }

    @Override
    public Booking createBooking(BookingRequestDTO dto, String email) {

        // 🔥 VALIDATION
        if (dto.getCheckIn().isAfter(dto.getCheckOut())) {
            throw new BadRequestException("Invalid date range");
        }

        // 🔥 CHECK AVAILABILITY FIRST
        AvailabilityDTO availabilityDTO = new AvailabilityDTO();
        availabilityDTO.setPropertyId(dto.getPropertyId());
        availabilityDTO.setFromDate(dto.getCheckIn());
        availabilityDTO.setToDate(dto.getCheckOut());

        boolean available = availabilityService.isAvailable(dto.getPropertyId(), availabilityDTO);

        if (!available) {
            throw new BadRequestException("Property not available for selected dates");
        }

        // ✅ CREATE BOOKING
        Booking booking = new Booking();
        booking.setUserEmail(email);
        booking.setPropertyId(dto.getPropertyId());
        booking.setCheckIn(dto.getCheckIn());
        booking.setCheckOut(dto.getCheckOut());
        booking.setGuests(dto.getGuests());
        booking.setStatus("CONFIRMED");

        Booking saved = bookingRepository.save(booking);

        // 🔥 BLOCK DATES AFTER BOOKING
        availabilityService.blockDates(availabilityDTO);

        logger.info("Booking {} created by {}", saved.getId(), email);
        return saved;
    }
    
    @Override
    public Booking cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus("CANCELLED");

        Booking saved = bookingRepository.save(booking);
        logger.info("Booking {} cancelled", saved.getId());
        return saved;
    }

    @Override
    public List<Booking> getUserBookings(String email) {

        List<Booking> list = new ArrayList<>();
        bookingRepository.findByUserEmail(email).forEach(list::add);

        logger.debug("Fetched {} bookings for {}", list.size(), email);
        return list;
    }
}
