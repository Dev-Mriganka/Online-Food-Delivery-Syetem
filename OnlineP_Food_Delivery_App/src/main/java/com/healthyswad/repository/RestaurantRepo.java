package com.healthyswad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthyswad.model.Address;
import com.healthyswad.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{

	public List<Restaurant> findByRestaurantName(String restaurantName);
	
	public Restaurant findByAddress(Address address);
	
	public Restaurant findByContactNumber(String number);
	
	public Restaurant findByEmail(String email);
	
}
