package com.healthyswad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.CustAddDto;
import com.healthyswad.dto.CustomerDto;
import com.healthyswad.dto.CustomerResDTO;
import com.healthyswad.dto.Viewprofile;
import com.healthyswad.exception.AddressException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Customer;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.FoodCartItems;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.AddressRepo;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.FoodCartDao;
import com.healthyswad.repository.FoodCartItemRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private AddressRepo ar;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Autowired
	private SessionRepo sessionrepo;

	@Autowired
	private FoodCartDao fd;
	
	@Autowired
	private FoodCartItemRepo fcir;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	//Register Customer -- Tested
	@Override
	public Customer registerCustomer(CustAddDto customer) throws CustomerException {
		
		Customer cust = cr.findByEmail(customer.getEmail());
		
		if(cust == null) {
			
			cust = cr.findByMobileNumber(customer.getMobileNumber());
			
			if(cust == null) {
				
				cust = this.modelMapper.map(customer, Customer.class);
				
				FoodCart fc = new FoodCart();
				
				fc.setCustomer(cust);
				
				cust.setFoodCart(fc);
				
				return cr.save(cust);

			}else {
				throw new CustomerException("Mobile already exists..");
				
			}
			
		}else {
			throw new CustomerException("Email already exists..");
			
		}
		
	}

	
	//Update Customer 
	@Override
	public Customer updateCustomer(CustAddDto customer, String key) throws CustomerException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customer.getCustomerId()) {
		
			Customer c = cr.findById(customer.getCustomerId())
					.orElseThrow(() ->  new CustomerException("You are not authorized.."));
			
			Customer cust = this.modelMapper.map(customer, Customer.class);
			
			if(customer.getPassword()== null || customer.getPassword().equals(""))
				cust.setPassword(c.getPassword());
				
			cust.setFoodCart(c.getFoodCart());
			
			cust.setOrders(c.getOrders());
			
			cust.setAddresses(c.getAddresses());
			
			return cr.save(cust);
			
		}else {
			
			throw new CustomerException("You are not authorized..");
			
		}
		
	}

	
	//Delete Customer --
	@Override
	public String deleteCustomer(Integer customerId, String key) throws CustomerException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customerId) {
			
			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));
			
//			System.out.println(cust.getFoodCart());
			
//			public List<FoodCartItems> = fcir.findByFc(cust.getFoodCart());
			
			cr.delete(cust);
			
			return "Customer Successfully Deleted...";
			
		}else {
			throw new CustomerException("You are not authorized..");
		}
		
	}

	
	//view Customer -- Tested
	@Override
	public Viewprofile viewProfile(Integer customerId, String key) throws CustomerException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customerId) {
			
			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));
			return this.modelMapper.map(cust, Viewprofile.class);
			
		}else {
			throw new CustomerException("You are not authorized Login With same ID..");
		}
		
	}

	
	//view All Customer In Restaurant -- Tested
	@Override
	public List<CustomerResDTO> viewAllCustomersInRestaurant(Integer restId, String key)
			throws RestaurantException, CustomerException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		if(curr.getUserId() == restId) {
			
			Restaurant restaurant =rr.findById(restId)
					.orElseThrow(() -> new RestaurantException(""));
			
			Set<Customer> customers = restaurant.getCustomers();
			
			List<CustomerResDTO> custDetails = new ArrayList<>();
			
			for(Customer c: customers) {
				
				CustomerResDTO cDTO = new CustomerResDTO(c.getFullName(), c.getAge(), c.getGender());
				
				List<OrderDetails> odList = c.getOrders();
				
				List<OrderDetails> restaurantOdList = new ArrayList<>();
				
				for(OrderDetails cd: odList) {
					
					if(cd.getRestaurant().getRestaurantId() == restId) {
						restaurantOdList.add(cd);
					}
					
				}
				
				cDTO.setOrders(restaurantOdList);
				
				custDetails.add(cDTO);
				
			}
			
			return custDetails;
			
		}else {
			
			throw new CustomerException("You are not authorized Login With same Restaurant ID..");
			
		}
		
	}

	
	//add Address -- Tested
	@Override
	public CustomerDto addAddress(Integer customerId, Address add, String key) throws CustomerException, AddressException{
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customerId) {
			
			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));
			
			Set<Address> addes = cust.getAddresses();
			
			Address address = ar.findByStreetPincodeBuilding(add.getStreet(), add.getPincode(), add.getBuilding());
			
			if(address != null) {
				
				throw new AddressException("Address already added..");
				
			}
			
			addes.add(add);
			
			cust.setAddresses(addes);
			
			cust = cr.save(cust);
			
			return this.modelMapper.map(cust, CustomerDto.class);
			
		}else {
			throw new CustomerException("You are not authorized..");
		}
		
	}

	
	//edit Address -- Tested
	@Override
	public CustomerDto editAddress(Integer customerId, Address add, String key) throws CustomerException, AddressException{
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customerId) {
			
			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));
			
			Set<Address> addes = cust.getAddresses();
			
			for(Address ad: addes) {
				
				if(ad.getAddressId() == add.getAddressId()) {
					
					ad.setBuilding(add.getBuilding());		
					ad.setCity(add.getCity());
					ad.setCountry(add.getCountry());
					ad.setPincode(add.getPincode());
					ad.setState(add.getState());
					ad.setStreet(add.getStreet());
					
					
					cust.setAddresses(addes);
					
//					System.out.println(cust);
					
					cr.save(cust);
					
					return this.modelMapper.map(cust, CustomerDto.class);
				}
				
			}
				
			throw new AddressException("Address is not found for customer..");
				
			}else {
				
			throw new CustomerException("You are not authorized..");
		}
		
	}

	
	//Remove Address -- Tested
	@Override
	public CustomerDto removeAddress(Integer customerId, Integer addId, String key) throws CustomerException, AddressException{
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new CustomerException("No customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new CustomerException("Log in as a customer..");
		
		if(curr.getUserId() == customerId) {
			
			Customer cust = cr.findById(customerId)
					.orElseThrow(() -> new CustomerException(""));
			
			Address add = ar.findById(addId)
					.orElseThrow(() -> new AddressException("No address present with is id.."));
			
			Set<Address> addes = cust.getAddresses();
			
			if(addes.contains(add)) {
				
				addes.remove(add);
				
				cust.setAddresses(addes);
				
				ar.delete(add);
				
			}else {
				
				throw new AddressException("Address is not added..");
				
			}
			
			return this.modelMapper.map(cust, CustomerDto.class);
			
		}else {
			throw new CustomerException("You are not authorized..");
		}
		
	}

}
