package com.healthyswad.dto;

import java.util.List;

import com.healthyswad.model.Address;
import com.healthyswad.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantDTO {
	
	private String restaurantName;
	private String contractNumber;
	private Address address;
	private List<Item> itemList;
	
}
