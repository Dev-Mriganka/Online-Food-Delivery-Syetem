package com.healthyswad.model;

import java.util.Map;

import javax.persistence.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
	
	private Integer customerId;
	private String fullName;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;
	
	private Map<Address, String> addresses;
	
	private FoodCart foodCart;
	
}
