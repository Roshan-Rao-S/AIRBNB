package com.airbnb.property.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.airbnb.property.dto.PropertyDTO;
import com.airbnb.property.entity.Property;
import com.airbnb.property.repository.PropertyRepository;
import com.airbnb.userservice.exception.ResourceNotFoundException;
import com.airbnb.userservice.exception.UnauthorizedException;

@Service
public class PropertyServiceImpl implements PropertyService {

    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @Override
    public Property addProperty(PropertyDTO dto, String email) {

        Property property = new Property();

        property.setTitle(dto.getTitle());
        property.setLocation(dto.getLocation());
        property.setPrice(dto.getPrice());
        property.setOwnerEmail(email);

        property.setImageUrl(dto.getImageUrl());

        // ✅ FIXED
        property.setRating(0.0);
        property.setReviewCount(0);

        Property saved = propertyRepository.save(property);
        logger.info("Property created with id {} by {}", saved.getId(), email);
        return saved;
    }

    @Override
    public List<Property> getAllProperties() {

        List<Property> list = new ArrayList<>();

        propertyRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
    }
    
 // 🔥 NEW: Get properties of logged-in host
   
    @Override
    public List<Property> getPropertiesByOwner(String email) {
        return propertyRepository.findByOwnerEmail(email);
    }

    // 🔥 NEW: Delete property
    @Override
    public void deleteProperty(Long id, String email) {

        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        if (!property.getOwnerEmail().equals(email)) {
            throw new UnauthorizedException("Unauthorized");
        }

        propertyRepository.deleteById(id);
        logger.info("Property {} deleted by {}", id, email);
    }
}
