package com.healthyswad.service;

import java.util.List;

import com.healthyswad.dto.OrderDTO;
import com.healthyswad.dto.RestOrderDto;
import com.healthyswad.exception.AddressException;
import com.healthyswad.exception.CartException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.OrderDetails;

public interface OrderDetailsService {
			
	
	public OrderDetails addDetails(Integer addressId, String key) throws OrderDetailsException, CustomerException, AddressException, CartException;
	
	public OrderDetails updateItem(Integer itemId, Integer orderId, String key) throws OrderDetailsException, CustomerException, CartException, ItemException;
	
	public OrderDetails updateAddress(Integer addressId, Integer orderId, String key) throws OrderDetailsException, CustomerException, CartException, AddressException;
	
	public OrderDTO updateStatus(Integer orderId, String key) throws OrderDetailsException, RestaurantException;
	
	public String cancelOrder(Integer orderId, String key) throws OrderDetailsException, CustomerException;
	
	public OrderDTO viewOrder(Integer orderId, String key)throws OrderDetailsException, CustomerException, RestaurantException;
	
	public List<OrderDTO> viewAllOrders(String key)throws OrderDetailsException, RestaurantException, CustomerException;
	
	public List<RestOrderDto> viewAllOrdersRestaurant(String key)throws OrderDetailsException, RestaurantException, CustomerException;
	
}
