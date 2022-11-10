package com.healthyswad.service;

import java.util.List;

import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Restaurant;

public interface RestaurantService {
	
	public Restaurant addRestaurant(Restaurant res) throws RestaurantExcaption;
	
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantExcaption;
	
	public Restaurant removeRestaurant(Restaurant res) throws RestaurantExcaption;
	
	public Restaurant viewRestaurant(Restaurant res) throws RestaurantExcaption;
	
	public List<Restaurant> viewNearByRestaurant(String city) throws RestaurantExcaption;
	
	public List<RestaurantDTO> viewRestaurantByItemName(String name) throws RestaurantExcaption;
	
}
