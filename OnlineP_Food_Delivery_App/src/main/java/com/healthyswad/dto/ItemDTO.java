package com.healthyswad.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
	
	
	private Integer itemId;
	
	@NotBlank(message = "Item Name is Mandatory")
	private String itemName;
	
	@NotBlank(message = "Description is Mandatory")
	private String description;
	
	@NotNull
	private Double cost;
	
	@URL
	private String imageUrl;
	
	@JsonIgnore
	private RestaurantDTO restDTO;
	
}
