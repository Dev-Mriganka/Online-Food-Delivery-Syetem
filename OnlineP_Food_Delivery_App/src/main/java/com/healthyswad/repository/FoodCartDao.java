package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.FoodCart;

public interface FoodCartDao extends JpaRepository<FoodCart, String>{

}
