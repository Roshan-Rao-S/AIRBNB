package com.airbnb.calendar.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.airbnb.calendar.entity.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

    List<Availability> findByPropertyId(Long propertyId);

    // check overlapping dates
    List<Availability> findByPropertyIdAndBlockedFromLessThanEqualAndBlockedToGreaterThanEqual(
            Long propertyId,
            LocalDate to,
            LocalDate from
    );
}