package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.OrderItems;



public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer>{

}
