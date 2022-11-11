package com.healthyswad.service;

import java.util.List;

import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Restaurant;

public interface RestaurantService {
	
	public Restaurant addRestaurant(Restaurant res) throws RestaurantException;
	
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantException;
	
	public Restaurant removeRestaurant(Restaurant res) throws RestaurantException;
	
	public Restaurant viewRestaurant(Restaurant res) throws RestaurantException;
	
	public List<Restaurant> viewNearByRestaurant(String city) throws RestaurantException;
	
	public List<RestaurantDTO> viewRestaurantByItemName(String name) throws RestaurantException;
	
}
