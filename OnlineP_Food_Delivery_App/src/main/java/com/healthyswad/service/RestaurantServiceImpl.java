package com.healthyswad.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.RestaurantAddDTO;
import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.AddressRepo;
import com.healthyswad.repository.ItemRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private AddressRepo ar;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Autowired
	private ItemRepo ir;
	
	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Restaurant addRestaurant(RestaurantAddDTO resDto) throws RestaurantException {
		
		Restaurant restaurant = rr.findByContactNumber(resDto.getContactNumber());
		
		if(restaurant == null) {
			
			restaurant = rr.findByEmail(resDto.getEmail());
			
			if(restaurant == null) {
				
				Restaurant rest  = this.modelMapper.map(resDto, Restaurant.class);
				
				return rr.save(rest);
				
			}else {
				
				throw new RestaurantException("Email already exists..");
				
			}
			
		}else {
			
			throw new RestaurantException("Mobile already exists..");
			
		}
			
		
	}

	
	@Override
	public Restaurant updateRestaurant(RestaurantAddDTO resDto, String key) throws RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		if(resDto.getRestaurantId() == curr.getUserId()) {
			Restaurant restaurant = rr.findById(resDto.getRestaurantId())
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
			restaurant = this.modelMapper.map(resDto, Restaurant.class);
			
			return rr.save(restaurant);
		}else {
			
			throw new RestaurantException("Enter Authorised Restaurant Id..");
			
		}
		
	}

	
	@Override
	public Restaurant removeRestaurant(Integer resId, String key) throws RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		if(resId == curr.getUserId()) {
			Restaurant restaurant = rr.findById(resId)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
			rr.delete(restaurant);
		
			return restaurant;
		
		}else {
			
			throw new RestaurantException("Enter Authorised Restaurant Id..");
			
		}

	}

	
	@Override
	public RestaurantDTO viewRestaurant(Integer resId) throws RestaurantException {
		
		Restaurant restaurant = rr.findById(resId)
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
		return this.modelMapper.map(restaurant, RestaurantDTO.class);
			
	}

	
	@Override
	public List<RestaurantDTO> viewNearByRestaurant(String city) throws RestaurantException {
		
		List<Address> addresses = ar.findByCity(city);
		
		if(addresses.size() == 0) {
			throw new RestaurantException("No restaurant found in your city...");
		}
		
		List<RestaurantDTO> restaurants = new ArrayList<>();
		
		for(Address ad: addresses) {
			
			Restaurant restaurant = rr.findByAddress(ad);
			
			if(restaurant != null) {
				RestaurantDTO rDto = this.modelMapper.map(restaurant, RestaurantDTO.class);
				restaurants.add(rDto);
				
			}
	
		}
		
		if(restaurants.size() == 0) {
			throw new RestaurantException("No restaurant found in your city...");
		}
		
		return restaurants;
		
	}

	
	@Override
	public Set<RestaurantDTO> viewRestaurantByItemName(String itemName) throws ItemException, RestaurantException{
		
		List<Item> itms = ir.findByItemNameContaining(itemName);
		
		System.out.println(itms);
		
		if(itms.size() == 0) {
			throw new ItemException("No such item exists...");
		}
		
		Set<RestaurantDTO> rDtos = new HashSet<>();
	

		for(Item i : itms) {

			RestaurantDTO rDto = this.modelMapper.map(i.getRestaurant(), RestaurantDTO.class);
			
		    rDtos.add(rDto);
		}
		
		if(rDtos.isEmpty())
			throw new RestaurantException("No Restaurants Had this Item ");
		
		return rDtos;
	}
	
}
