package com.healthyswad.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Restaurant {
	
	private String restaurantId;
	private String restaurantName;
	private String managerName;
	private String contractNumber;
	
	private Address address;
	
	private List<Item> itemList;
	
}
