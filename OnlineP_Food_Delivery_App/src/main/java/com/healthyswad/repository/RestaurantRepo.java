package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthyswad.model.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{

//	public Customer findByEmail(String email);
//	
//	public Customer findByMobileNumber(String number);
	
}
