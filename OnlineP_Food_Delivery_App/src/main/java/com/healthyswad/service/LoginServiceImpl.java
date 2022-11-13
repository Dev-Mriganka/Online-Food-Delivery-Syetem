package com.healthyswad.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.LoginDTO;
import com.healthyswad.exception.LoginException;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Customer;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private SessionRepo sr;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Override
	public String logIntoAccount(LoginDTO dto)throws LoginException{
		
		if(dto.getRole().equalsIgnoreCase("customer")) {
			
			Customer existingCustomer= cr.findByMobileNumber(dto.getMobileNo());
			
			if(existingCustomer == null) {
				
				throw new LoginException("Please Enter a valid mobile number");
				
			}
			
			Optional<CurrentUserSession> validCustomerSessionOpt =  sr.findById(existingCustomer.getCustomerId());
			
			if(validCustomerSessionOpt.isPresent()) {
				
				throw new LoginException("User already Logged In with this number");
				
			}
			
			if(existingCustomer.getPassword().equals(dto.getPassword())) {
				
				String key= RandomString.make(4)+existingCustomer.getCustomerId()+RandomString.make(4);
			
				CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),dto.getRole(),key,LocalDateTime.now());
				
				sr.save(currentUserSession);
	
				return currentUserSession.toString();
			}
			else
				throw new LoginException("Please Enter a valid password");
			
		}else if(dto.getRole().equalsIgnoreCase("restaurant")){
			
			Restaurant existingRestaurant= rr.findByContactNumber(dto.getMobileNo());
			
			if(existingRestaurant == null) {
				
				throw new LoginException("Please Enter a valid mobile number");
				
			}
			
			Optional<CurrentUserSession> validCustomerSessionOpt =  sr.findById(existingRestaurant.getRestaurantId());
			
			if(validCustomerSessionOpt.isPresent()) {
				
				throw new LoginException("Restaurant already Logged In with this number");
				
			}
			
			if(existingRestaurant.getPassword().equals(dto.getPassword())) {
				
				String key= RandomString.make(4)+existingRestaurant.getRestaurantId()+RandomString.make(4);
			
				CurrentUserSession currentUserSession = new CurrentUserSession(existingRestaurant.getRestaurantId(),dto.getRole(),key,LocalDateTime.now());
				
				sr.save(currentUserSession);
	
				return currentUserSession.toString();
			}
			else
				throw new LoginException("Please Enter a valid password");
			
		}else {
			
			throw new LoginException("Please Enter a valid role");
			
		}
		
		
	}


	@Override
	public String logOutFromAccount(String key)throws LoginException {
		
		CurrentUserSession validCustomerSession = sr.findByUuid(key);
		
		
		if(validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this number");
			
		}
		
		sr.delete(validCustomerSession);
		
		
		return "Logged Out !";
		
		
	}

}
