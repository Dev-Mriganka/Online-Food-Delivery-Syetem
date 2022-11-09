package com.healthyswad.model;


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
	private String quantity;
	private Double cost;
	private String imangeUrl;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;
	
	
	
}
