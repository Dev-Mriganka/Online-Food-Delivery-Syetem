package com.healthyswad.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.healthyswad.dto.CustAddDto;
import com.healthyswad.dto.CustomerDto;
import com.healthyswad.dto.CustomerResDTO;
import com.healthyswad.dto.Viewprofile;
import com.healthyswad.exception.AddressException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.Customer;

@Repository
public interface CustomerService {

	public Customer registerCustomer(CustAddDto customer) throws CustomerException;
	
	public Customer updateCustomer(CustAddDto customer, String key) throws CustomerException;
	
	public String deleteCustomer(Integer customerId, String key) throws CustomerException;
	
	public Viewprofile viewProfile(Integer customerId, String key) throws CustomerException;
	
	public List<CustomerResDTO> viewAllCustomersInRestaurant(Integer restId, String key)throws RestaurantException, CustomerException;
	
	public CustomerDto addAddress(Integer customerId, Address add, String key) throws CustomerException, AddressException;
	
	public CustomerDto editAddress(Integer customerId, Address add, String key) throws CustomerException, AddressException;
	
	public CustomerDto removeAddress(Integer customerId, Integer addId, String key) throws CustomerException, AddressException;
}
