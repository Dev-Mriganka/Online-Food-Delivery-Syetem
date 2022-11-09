package com.healthyswad.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class FoodCart {
	
	private Integer cartId;
	
	private Customer customer;
	
	private List<Item> itemList;
	
}
