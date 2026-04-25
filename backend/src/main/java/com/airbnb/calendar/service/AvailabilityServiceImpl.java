package com.airbnb.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.calendar.dto.AvailabilityDTO;
import com.airbnb.calendar.entity.Availability;
import com.airbnb.calendar.repository.AvailabilityRepository;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository repository;

    @Override
    public Availability blockDates(AvailabilityDTO dto) {

        Availability a = new Availability();
        a.setPropertyId(dto.getPropertyId());
        a.setBlockedFrom(dto.getFromDate());
        a.setBlockedTo(dto.getToDate());

        return repository.save(a);
    }

    @Override
    public List<Availability> getAvailability(Long propertyId) {
        return repository.findByPropertyId(propertyId);
    }

    @Override
    public boolean isAvailable(Long propertyId, AvailabilityDTO dto) {

        List<Availability> conflicts =
                repository.findByPropertyIdAndBlockedFromLessThanEqualAndBlockedToGreaterThanEqual(
                        propertyId,
                        dto.getToDate(),
                        dto.getFromDate()
                );

        return conflicts.isEmpty();
    }
}