package com.healthyswad.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String fullName;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	private Map<Address, String> addresses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "")
	private List<OrderDetails> orders;
	
	@ManyToMany(cascade = CascadeType.ALL) 
	private Set<Restaurant> restaurants;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private FoodCart foodCart;
	
	
}
