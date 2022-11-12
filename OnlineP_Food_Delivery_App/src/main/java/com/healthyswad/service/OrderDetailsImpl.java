package com.healthyswad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Customer;
import com.healthyswad.model.Item;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.OrderDetailsRepo;
import com.healthyswad.repository.RestaurantRepo;

public class OrderDetailsImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepo odr;
	
	@Autowired
	private CustomerRepo cur;
	
	@Autowired
	private RestaurantRepo rp;

	@Override
	public OrderDetails addDetails(OrderDetails order) throws OrderDetailsException {
		
		OrderDetails ord= odr.save(order);
		
		return ord;
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) throws OrderDetailsException {
		
		odr.findById(order.getOrderId()).orElseThrow(() -> new OrderDetailsException("Order not available with this id " ));
		
		return odr.save(order);
	}

	@Override
	public OrderDetails removeOrder(OrderDetails order) throws OrderDetailsException {
		
		OrderDetails ord = odr.findById(order.getOrderId())
				.orElseThrow(() -> new OrderDetailsException("No record exists..."));
		
		odr.delete(order);
		
		return ord;
	}

	@Override
	public OrderDetails viewOrder(OrderDetails order) throws OrderDetailsException {
		
		OrderDetails ods = odr.findById(order.getOrderId()).orElseThrow(() -> new OrderDetailsException("No records found ....."));
		
		return ods;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant res) throws OrderDetailsException,RestaurantExcaption {

		Restaurant rust= rp.findById(res.getRestaurantId()).orElseThrow(() -> new RestaurantExcaption("No Restaurant found with this id"));
		
		List<OrderDetails> itm = rust.getOrderLists();
		
		return itm;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailsException,CustomerException {
		
		Customer cust= cur.findById(customer.getCustomerId()).orElseThrow(() -> new CustomerException("No customer found with this id"));
		
		List<OrderDetails> itm = cust.getOrders();
		
		return itm;
	}

}
