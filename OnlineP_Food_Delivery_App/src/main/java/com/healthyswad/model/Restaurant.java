package com.healthyswad.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

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
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String restaurantName;
	private String managerName;
	private String contractNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Item> itemList = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Item> orderLists = new ArrayList<>();
	

	@ManyToMany(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Customer> customers = new ArrayList<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		return Objects.equals(address, other.address) && Objects.equals(contractNumber, other.contractNumber)
				&& Objects.equals(managerName, other.managerName)
				&& Objects.equals(restaurantName, other.restaurantName);
	}


	@Override
	public int hashCode() {
		return Objects.hash(address, contractNumber, managerName, restaurantName);
	}

	
	
	
}
