package com.healthyswad.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	private LocalDateTime orderDate;
	private String orderStatus;
	
//	private Address orderAddress;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Restaurant restaurant;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private FoodCart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private  Bill bill;

	
}
