package com.healthyswad.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


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
	
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	private Map<Address, String> addresses;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderDetails> orders;
	
	
	@OneToOne(targetEntity = FoodCart.class, cascade = CascadeType.ALL)
	private FoodCart foodCart;


	public Customer(Integer customerId, String fullName, Integer age, String gender, String mobileNumber, String email,
			String password, FoodCart foodCart) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.foodCart = foodCart;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(addresses, other.addresses) && Objects.equals(email, other.email)
				&& Objects.equals(mobileNumber, other.mobileNumber);
	}


	@Override
	public int hashCode() {
		return Objects.hash(addresses, email, mobileNumber);
	}
	
	
}
