package com.airbnb.calendar.service;

import java.util.List;

import com.airbnb.calendar.dto.AvailabilityDTO;
import com.airbnb.calendar.entity.Availability;

public interface AvailabilityService {

    Availability blockDates(AvailabilityDTO dto);

    List<Availability> getAvailability(Long propertyId);

    boolean isAvailable(Long propertyId, AvailabilityDTO dto);
}