package com.healthyswad.dto;

import java.util.List;

import com.healthyswad.model.Category;
import com.healthyswad.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	
	private String itemName;
	private Double cost;
	private String imangeUrl;
	
	private List<Category> category;
	
	private List<Restaurant> restaurant;
	
	

}
