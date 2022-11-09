package com.healthyswad.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {
	
	private Integer orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	
	private Address orderAddress;
	
	private FoodCart cart;
	
	private  Bill bill;
	
}
