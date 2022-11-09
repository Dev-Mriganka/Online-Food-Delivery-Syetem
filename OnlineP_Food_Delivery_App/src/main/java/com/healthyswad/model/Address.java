package com.healthyswad.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	
	private Integer addressId;
	private String area;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
}
