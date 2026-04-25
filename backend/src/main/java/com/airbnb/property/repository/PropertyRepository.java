package com.airbnb.property.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.airbnb.property.entity.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {
	 List<Property> findByOwnerEmail(String email);

}