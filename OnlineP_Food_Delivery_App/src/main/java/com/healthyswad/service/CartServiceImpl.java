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
	public FoodCart addCart(FoodCart cart) {

		return foodCartDao.save(cart);
	}


	@Override
	public FoodCart increaseQuantity(Item item, Integer quantity, String key) throws RestaurantException, ItemException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No Customer Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("restaurant")) throw new RestaurantException("You are not authorized..");
		
		Customer customer = customerrepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));

		List<Item> items = customer.getFoodCart().getItemList();

		for (Item i : items) {

			if (i.equals(item)) {

				i.setQuantity(i.getQuantity() + quantity);

				foodCartDao.save(cart);

			}

		}

		return cart;
	}

	@Override
	public FoodCart reduceQuantity(FoodCart cart, Item item, Integer quantity) throws ItemException, CartException {
		foodCartDao.findById(cart.getCartId()).orElseThrow(() ->

		new CartException("cart is not found!!")

		);

		List<Item> items = cart.getItemList();

		for (Item i : items) {

			if (i.equals(item)) {

				i.setQuantity(i.getQuantity() - quantity);

				foodCartDao.save(cart);

			}

		}

		return cart;

	}

	@Override
	public FoodCart removeItem(FoodCart cart, Item item) throws ItemException, CartException {
	
		foodCartDao.findById(cart.getCartId()).orElseThrow(() ->

		new CartException("cart is not found!!")

		);

		List<Item> items = cart.getItemList();

		for (Item i : items) {

			if (i.equals(item)) {

				items.remove(i);

				foodCartDao.save(cart);

			}

		}

		return cart;
		
	
	}

	@Override
	public FoodCart clearCart(FoodCart cart) throws CartException {
		
		foodCartDao.findById(cart.getCartId()).orElseThrow(() ->

		new CartException("cart is not found!!")

		);

		cart.setItemList(new ArrayList<>());
		
		return cart;
	}



}
