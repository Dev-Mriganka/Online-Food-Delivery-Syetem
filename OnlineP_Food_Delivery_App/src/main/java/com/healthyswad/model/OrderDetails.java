package com.healthyswad.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
//	private Address orderAddress;
	
	@OneToOne
	private Restaurant restaurant;
	
	@OneToOne
	private FoodCart cart;
	
	@OneToOne
	private  Bill bill;

	
}
