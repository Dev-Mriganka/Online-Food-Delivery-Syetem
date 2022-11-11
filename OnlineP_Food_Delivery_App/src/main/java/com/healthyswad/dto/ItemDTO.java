package com.healthyswad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	private Integer itemId;
	private String itemName;
	private String description;
	private Double cost;
	private String imageUrl;
	private RestaurantDTO restDTO;
	
}
