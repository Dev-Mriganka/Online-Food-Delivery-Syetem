package com.healthyswad.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.healthyswad.model.Address;
import com.healthyswad.model.Bill;
import com.healthyswad.model.OrderItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	private Integer orderId;
	private LocalDateTime orderDate;
	private Boolean orderStatus;
	
	private Address orderAddress;
	
	private RestSimpleDTO restaurant;
	
	private List<OrderItems> itemList = new ArrayList<>();
	
	private  Bill bill;

}
