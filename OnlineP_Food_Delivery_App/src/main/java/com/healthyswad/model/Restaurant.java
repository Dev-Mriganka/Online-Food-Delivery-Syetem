package com.healthyswad.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Restaurant {
	
	@Id
	private String restaurantId;
	private String restaurantName;
	private String managerName;
	private String contractNumber;
	
	@Embedded
	private Address address;
	
	private List<Item> itemList;
	
}
