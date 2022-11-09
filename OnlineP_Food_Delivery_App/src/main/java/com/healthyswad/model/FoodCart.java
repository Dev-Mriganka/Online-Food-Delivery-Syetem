package com.healthyswad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FoodCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
	@OneToOne
	private Customer customer;
	
	
	private List<Item> itemList;
	
}
