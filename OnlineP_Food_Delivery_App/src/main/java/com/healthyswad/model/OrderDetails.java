package com.healthyswad.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {
	
	@Id
	private Integer orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	
	private Address orderAddress;
	
	private FoodCart cart;
	
	private  Bill bill;
	
}
