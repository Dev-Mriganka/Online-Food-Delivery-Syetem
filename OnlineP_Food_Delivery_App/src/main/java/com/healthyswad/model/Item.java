package com.healthyswad.model;



import java.util.Objects;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	private String itemName;
	private String Description;
	private Double cost;
	private String imangeUrl;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(imangeUrl, other.imangeUrl) && Objects.equals(restaurant, other.restaurant);
	}

	@Override
	public int hashCode() {
		return Objects.hash(imangeUrl, restaurant);
	}

	
	
	
}
