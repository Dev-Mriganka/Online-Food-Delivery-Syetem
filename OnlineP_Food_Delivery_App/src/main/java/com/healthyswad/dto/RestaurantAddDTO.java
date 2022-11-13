package com.healthyswad.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.healthyswad.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAddDTO {
	
	private Integer restaurantId;
	
	@NotBlank(message = "Restaurant Name is Mandatory")
	private String restaurantName;
	
	@Size(min = 3, message = "manager Name is Mandatory")
	private String managerName;
	
	@NotBlank
	@Size(min = 10, max = 12, message = "mobile must be between 10 to 12 digit")
	private String contactNumber;
	
	@NotBlank
	@Email(message = "Give proper Email Id")
	private String email;
	
	@NotBlank
	@Size(min = 6, message = "Password Size must be greater than 6")
	private String password;
	
	@Valid
	private Address address;
	
}
