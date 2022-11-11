package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
			
}
