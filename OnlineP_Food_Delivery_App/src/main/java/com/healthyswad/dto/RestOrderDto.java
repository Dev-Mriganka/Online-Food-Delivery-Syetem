package com.healthyswad.dto;



import com.healthyswad.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestOrderDto {
	
	private Integer restaurantId;
	private String restaurantName;
	private String contractNumber;
	private Address address;
	
}
