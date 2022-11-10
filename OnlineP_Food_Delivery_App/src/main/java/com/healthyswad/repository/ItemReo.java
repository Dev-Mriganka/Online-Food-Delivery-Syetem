package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.Item;

public interface ItemReo extends JpaRepository<Item, Integer>{

	Item findByItemName(String itemName);
	
}
