package com.healthyswad.model;



import java.util.Objects;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private Integer quantity;
	private Double cost;
	private String imangeUrl;
	
	@ManyToOne(fetch = FetchType.EAGER)
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
		return Objects.equals(category, other.category) && Objects.equals(cost, other.cost)
				&& Objects.equals(imangeUrl, other.imangeUrl) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(itemName, other.itemName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, cost, imangeUrl, itemId, itemName);
	}
	
	
}
