package com.airbnb.property.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airbnb.property.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	 List<Property> findByOwnerEmail(String email);

}
