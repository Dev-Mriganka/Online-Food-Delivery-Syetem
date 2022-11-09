package com.healthyswad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.CustomerDTO;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Customer;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.RestaurantRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Customer cust = cr.findByEmail(customer.getEmail());
		
		if(cust == null) {
			
			cust = cr.findByMobileNumber(customer.getMobileNumber());
			
			if(cust == null) {
				
				return cr.save(customer);

			}else {
				throw new CustomerException("Mobile already exists..");
				
			}
			
		}else {
			throw new CustomerException("Email already exists..");
			
		}

	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
	
		cr.findById(customer.getCustomerId())
			.orElseThrow(() -> new CustomerException("No such customer exists.."));
		
		return cr.save(customer);
			
	}

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerException {
		
		Customer cust = cr.findById(customer.getCustomerId())
		.orElseThrow(() -> new CustomerException("No such customer exists.."));
	
		cr.delete(customer);
		
		return cust;
		
	}

	@Override
	public Customer viewCustomer(Customer customer) throws CustomerException {
		
		Customer cust = cr.findById(customer.getCustomerId())
				.orElseThrow(() -> new CustomerException("No such customer exists.."));
			
		cr.delete(customer);
				
		return cust;
	}

	@Override
	public List<CustomerDTO> viewAllCustomersInRestaurant(Restaurant rest) throws RestaurantExcaption, CustomerException {
		
		Restaurant restaurant =rr.findById(rest.getRestaurantId())
			.orElseThrow(() -> new RestaurantExcaption("No such restaurant exists.."));
		
		Set<Customer> customers = restaurant.getCustomers();
		
		List<CustomerDTO> custDetails = new ArrayList<>();
		for(Customer c: customers) {
			
			CustomerDTO cDTO = new CustomerDTO(c.getFullName(), c.getAge(), c.getGender());
			
			Set<OrderDetails> odList = c.getOrders();
			
			List<OrderDetails> restaurantOdList = new ArrayList<>();
			
			for(OrderDetails cd: odList) {
				
				
				
			}
			
			custDetails.add(cDTO);
		}
		
		return custDetails;
	}

}
