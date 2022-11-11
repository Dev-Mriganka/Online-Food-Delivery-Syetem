package com.healthyswad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.Category;
import com.healthyswad.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

//	@Query("select i.restaurant from Item i where i.itemName = ?1")
	public List<Item> findByItemNameContaining(String itemName);

	public Item findByItemName(String itemName);

	public List<Item> findByCategory(Category category);

}
