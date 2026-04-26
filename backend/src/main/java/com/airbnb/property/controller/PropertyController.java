package com.airbnb.property.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbnb.property.dto.PropertyDTO;
import com.airbnb.property.entity.Property;
import com.airbnb.property.service.PropertyService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/properties")
public class PropertyController {

	private final PropertyService propertyService;

	@Autowired
	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	// 🔐 Add property (protected)
	@PostMapping
	public ResponseEntity<Property> addProperty(@jakarta.validation.Valid @RequestBody PropertyDTO dto, HttpServletRequest request) {

		String email = (String) request.getAttribute("email");

		Property property = propertyService.addProperty(dto, email);

		return ResponseEntity.ok(property);
	}

	// 🌍 Get all
	@GetMapping
	public ResponseEntity<List<Property>> getAll() {
		return ResponseEntity.ok(propertyService.getAllProperties());
	}

	// 🔍 Get by ID
	@GetMapping("/{id}")
	public ResponseEntity<Property> getById(@PathVariable Long id) {
		return ResponseEntity.ok(propertyService.getPropertyById(id));
	}

	// 🔥 HOST PROPERTIES
	// 🔥 HOST PROPERTIES
	@GetMapping("/host")
	public ResponseEntity<List<Property>> getHostProperties(HttpServletRequest request) {

	    String email = (String) request.getAttribute("email");

	    return ResponseEntity.ok(
	            propertyService.getPropertiesByOwner(email)
	    );
	}

	// 🔥 DELETE PROPERTY
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProperty(@PathVariable Long id,
	                                             HttpServletRequest request) {

	    String email = (String) request.getAttribute("email");

	    propertyService.deleteProperty(id, email);

	    return ResponseEntity.ok("Deleted");
	}
}
