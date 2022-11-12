package com.healthyswad.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CartException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Customer;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.FoodCartDao;
import com.healthyswad.repository.ItemRepo;
import com.healthyswad.repository.SessionRepo;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private FoodCartDao foodCartDao;

	@Autowired
	private ItemRepo itemDao;

	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private CustomerRepo customerrepo;
	
	
	@Override
	public FoodCart addItemToCart(Item item, String key) throws RestaurantException, ItemException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized..");
		
		Customer customer = customerrepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		
		itemDao.findById(item.getItemId())
			.orElseThrow(() ->new ItemException("Item id not valid!!!"));
		
		
		
		FoodCart fc = customer.getFoodCart();
		
		fc.getItemList().put(item, 1);

		return foodCartDao.save(fc);
	
	}


	@Override
	public FoodCart addCart(String key) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FoodCart increaseQuantity(Item item, Integer quantity, String key)
			throws RestaurantException, ItemException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FoodCart reduceQuantity(Item item, Integer quantity, String key) throws ItemException, CartException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FoodCart removeItem(Item item, String key) throws ItemException, CartException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public FoodCart clearCart(String key) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}
	
	



}
