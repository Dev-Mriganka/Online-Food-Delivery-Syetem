package com.healthyswad.model;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
	
	@Id
	private Integer itemId;
	private String itemName;
	private String quantity;
	private Double cost;
	
	@Embedded
	private Category category;
	
	private Restaurant restaurants;
	
}
