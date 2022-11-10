package com.healthyswad.model;


<<<<<<< Updated upstream
import javax.persistence.Embedded;
=======
import java.util.Objects;

import javax.persistence.CascadeType;
>>>>>>> Stashed changes
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Item {
	
<<<<<<< Updated upstream
=======
	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getImangeUrl() {
		return imangeUrl;
	}

	public void setImangeUrl(String imangeUrl) {
		this.imangeUrl = imangeUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

>>>>>>> Stashed changes
	@Id
	private Integer itemId;
	private String itemName;
	private Integer quantity;
	private Double cost;
	
	@Embedded
	private Category category;
	
<<<<<<< Updated upstream
	private Restaurant restaurants;
=======
	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

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
	
	
	
>>>>>>> Stashed changes
	
}
