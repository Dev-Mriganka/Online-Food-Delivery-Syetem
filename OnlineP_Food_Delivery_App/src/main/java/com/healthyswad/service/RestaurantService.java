package com.healthyswad.service;

import java.util.List;
import java.util.Set;

import com.healthyswad.dto.RestaurantAddDTO;
import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Restaurant;

public interface RestaurantService {
	
	public Restaurant addRestaurant(RestaurantAddDTO res) throws RestaurantException;
	
	public Restaurant updateRestaurant(RestaurantAddDTO res, String key) throws RestaurantException;
	
	public Restaurant removeRestaurant(Integer resId, String key) throws RestaurantException;
	
	public RestaurantDTO viewRestaurant(Integer resId) throws RestaurantException;
	
	public List<RestaurantDTO> viewNearByRestaurant(String city) throws RestaurantException;
	
	public Set<RestaurantDTO> viewRestaurantByItemName(String name) throws ItemException, RestaurantException;

	
}
