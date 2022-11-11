package com.healthyswad.model;



import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	private String itemName;
	private String description;
	private Double cost;
	private String imangeUrl;
	private Integer quantity;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
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
