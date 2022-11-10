package com.healthyswad.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FoodCart {
	
	@Id
	private String cartId;
	
	private Customer customer;
	
	private List<Item> itemList;
	
	
	//starting from here...
}
