package com.healthyswad.service;

import java.util.List;


import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;

public interface ItemService {
	
	public Item addItem(Item item) throws ItemException;
	
	public Item updateItem(Item item) throws ItemException;
	
	public Item viewItem(Item item) throws ItemException;
	
	public Item removeItem(Item item) throws ItemException;
	
	public List<Item> viewAllItemsByCategory(Category category) throws CategoryException;
	
	public List<Item> viewAllItemsByRestaurant(Restaurant restaurant) throws RestaurantExcaption;
	

	public List<Item> viewAllItemsByName(String name)throws ItemException;
	


}
