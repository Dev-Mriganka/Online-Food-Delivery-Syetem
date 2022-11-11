package com.healthyswad.service;

import java.util.List;

import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.model.Customer;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.Restaurant;

public interface OrderDetailsService {
			
	
	public OrderDetails addDetails(OrderDetails order) throws OrderDetailsException;
	
	public OrderDetails updateOrder(OrderDetails order) throws OrderDetailsException;
	
	public OrderDetails removeOrder(OrderDetails order) throws OrderDetailsException;
	
	public OrderDetails viewOrder(OrderDetails order)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Restaurant res)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailsException;
	
}
