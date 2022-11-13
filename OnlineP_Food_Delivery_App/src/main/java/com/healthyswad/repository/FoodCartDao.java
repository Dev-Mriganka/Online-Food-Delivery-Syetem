package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthyswad.model.FoodCart;


@Repository
public interface FoodCartDao extends JpaRepository<FoodCart, Integer>{

	
}
