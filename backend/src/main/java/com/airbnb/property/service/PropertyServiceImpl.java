package com.airbnb.property.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airbnb.property.dto.PropertyDTO;
import com.airbnb.property.entity.Property;
import com.airbnb.property.repository.PropertyRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


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

        return propertyRepository.save(property);
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
                .orElseThrow(() -> new RuntimeException("Property not found"));
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
                .orElseThrow(() -> new RuntimeException("Property not found"));

        if (!property.getOwnerEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        propertyRepository.deleteById(id);
    }
}