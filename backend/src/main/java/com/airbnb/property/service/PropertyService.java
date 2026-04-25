package com.airbnb.property.service;

import java.util.List;

import com.airbnb.property.dto.PropertyDTO;
import com.airbnb.property.entity.Property;

public interface PropertyService {

	Property addProperty(PropertyDTO dto, String email);

	List<Property> getAllProperties();

	Property getPropertyById(Long id);

	// 🔥 ADD THESE METHODS

	List<Property> getPropertiesByOwner(String email);

	void deleteProperty(Long id, String email);
}