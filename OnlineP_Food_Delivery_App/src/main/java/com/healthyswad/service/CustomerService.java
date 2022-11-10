package com.healthyswad.service;

import java.util.List;

import com.healthyswad.dto.CustomerDTO;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Customer;
import com.healthyswad.model.Restaurant;

public interface CustomerService {

	public Customer registerCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer) throws CustomerException;
	
	public Customer deleteCustomer(Customer customer) throws CustomerException;
	
	public Customer viewCustomer(Customer customer) throws CustomerException;
	
//	public List<Customer> viewCustomerByName(String name) throws CustomerException;
	
	public List<CustomerDTO> viewAllCustomersInRestaurant(Restaurant rest)throws RestaurantExcaption, CustomerException;
	
//	public List<Customer> viewAllCustomer() throws CustomerException;
}
