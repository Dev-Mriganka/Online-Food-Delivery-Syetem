package com.healthyswad.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Bill {
	
	@Id
	private Integer billId;
	private LocalDateTime billDate;
	private LocalDateTime billTime;
	private Double totalCost;
	private Integer totalItem;
	
	private OrderDetails order;
	
}
