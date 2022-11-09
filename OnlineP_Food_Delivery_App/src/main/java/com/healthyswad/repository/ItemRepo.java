package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{

	
	
}
