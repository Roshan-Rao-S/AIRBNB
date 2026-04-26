package com.airbnb.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.airbnb.calendar.dto.AvailabilityDTO;
import com.airbnb.calendar.entity.Availability;
import com.airbnb.calendar.service.AvailabilityService;

@RestController
@RequestMapping("/calendar")
public class AvailabilityController {

    private final AvailabilityService service;

    @Autowired
    public AvailabilityController(AvailabilityService service) {
        this.service = service;
    }

    // block dates
    @PostMapping("/block")
    public Availability block(@jakarta.validation.Valid @RequestBody AvailabilityDTO dto) {
        return service.blockDates(dto);
    }

    // check availability
    @PostMapping("/check")
    public boolean check(@jakarta.validation.Valid @RequestBody AvailabilityDTO dto) {
        return service.isAvailable(dto.getPropertyId(), dto);
    }

    // view
    @GetMapping("/{propertyId}")
    public List<Availability> get(@PathVariable Long propertyId) {
        return service.getAvailability(propertyId);
    }
}
