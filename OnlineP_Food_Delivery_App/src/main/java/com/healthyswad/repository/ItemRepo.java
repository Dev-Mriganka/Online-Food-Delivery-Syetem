package com.healthyswad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	
	@Query("select i.restaurant from Item i where i.itemName = ?1")
	public List<Restaurant> searchByItemName(String itemName);


	public Item findByItemName(String itemName);


	public List<Item> findByCategory(Category category);

     
	
	@Query("select title from Item where title like = %?1")
	public Item findByItemNames(String name);


	
	
}
