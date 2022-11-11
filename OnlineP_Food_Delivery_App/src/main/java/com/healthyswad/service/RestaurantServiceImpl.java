package com.healthyswad.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.AddressRepo;
import com.healthyswad.repository.ItemRepo;
import com.healthyswad.repository.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private AddressRepo ar;
	
	@Autowired
	private RestaurantRepo rr;
	
	@Autowired
	private ItemRepo ir;
	
	@Override
	public Restaurant addRestaurant(Restaurant res) throws RestaurantException {
		
		ar.save(res.getAddress());
		
		return rr.save(res);
		
	}

	
	@Override
	public Restaurant updateRestaurant(Restaurant res) throws RestaurantException {
		
		rr.findById(res.getRestaurantId())
			.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
		
		return rr.save(res);
		
	}

	
	@Override
	public Restaurant removeRestaurant(Restaurant res) throws RestaurantException {
		
		Restaurant restaurant = rr.findById(res.getRestaurantId())
			.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
		
		rr.delete(res);
		
		return restaurant;
		
	}

	
	@Override
	public Restaurant viewRestaurant(Restaurant res) throws RestaurantException {
		
		Restaurant restaurant = rr.findById(res.getRestaurantId())
				.orElseThrow(() -> new RestaurantException("No such restaurant exists..."));
			
		return restaurant;
			
	}

	
	@Override
	public List<Restaurant> viewNearByRestaurant(String city) throws RestaurantException {
		
		List<Address> addresses = ar.findByCity(city);
		
		if(addresses.size() == 0) {
			throw new RestaurantException("No restaurant found in your city...");
		}
		
		List<Restaurant> restaurants = new ArrayList<>();
		
		for(Address ad: addresses) {
			
			Restaurant restaurant = rr.findByAddress(ad);
			
			if(restaurant != null) {
				restaurants.add(restaurant);
				
			}
			
		}
		
		if(restaurants.size() == 0) {
			throw new RestaurantException("No restaurant found in your city...");
		}
		
		return null;
		
	}

	
	@Override
	public List<RestaurantDTO> viewRestaurantByItemName(String itemName) throws ItemException, RestaurantException {
		
		Item item = ir.findByItemName(itemName);
		
	if(item==null)
		throw new ItemException("Item Not Found in Any Restaraunt");
	
		List<Restaurant> restaurants = item.getRestaurants();
		
		List<RestaurantDTO> rDtos = new ArrayList<>();
	
		
		for(Restaurant res : restaurants) {
			
			RestaurantDTO rDto = new RestaurantDTO(res.getRestaurantName(), res.getContractNumber(),res.getAddress());
			
		      rDtos.add(rDto);
			
		}
		
		if(rDtos.isEmpty())
			throw new RestaurantException("No Restaurants Had this Item ");
		
		
		return rDtos;
	}
	
}
