package com.healthyswad.dto;

import com.healthyswad.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAddDTO {
	
	private Integer restaurantId;
	private String restaurantName;
	private String managerName;
	private String contactNumber;
	private String email;
	private String password;
	
	private Address address;
	
}
