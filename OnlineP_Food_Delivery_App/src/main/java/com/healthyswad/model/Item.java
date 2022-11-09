package com.healthyswad.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
	
	private Integer itemId;
	private String itemName;
	private String quantity;
	private Double cost;
	
	private Category category;
	
	private List<Restaurant> restaurants;
	
}
