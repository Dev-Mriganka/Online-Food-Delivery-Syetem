package com.healthyswad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.healthyswad.exception.CartException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.model.Customer;

import com.healthyswad.model.FoodCart;
import com.healthyswad.model.Item;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.FoodCartDao;


import com.healthyswad.repository.RestaurantRepo;

import com.healthyswad.repository.ItemRepo;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private FoodCartDao foodCartDao;

	@Autowired
	private ItemRepo itemDao;


	
	
	@Override
	public FoodCart addItemToCart(FoodCart cart, Item item) throws CartException, ItemException {
		
		foodCartDao.findById(cart.getCartId()).orElseThrow(() ->

		new CartException("cart is not found!!")

		);
		
		
		itemDao.findById(item.getItemId()).orElseThrow(() ->

		new ItemException("Item id not valid!!!")

		);
		
//		FoodCart c = cus.getFoodCart();

		cart.getItemList().add(item);

		return foodCartDao.save(cart);
		
	
	}
	
	
//	@Override
//	public FoodCart addItemToCart(Integer itemId, Integer customerId) throws CustomerException, ItemException {
//
//		Item item = itemDao.findById(itemId).orElseThrow(() ->
//
//		new ItemException("Item id not valid!!!")
//
//		);
//
////		if(!item.isPresent()) {
////			
////			throw new ItemException("Item id not valid!!!");
////		}
//
//		Customer cus = customerDao.findById(customerId).orElseThrow(() ->
//
//		new CustomerException("customer id not valid!!!")
//
//		);
//
////        if(!cus.isPresent()) {
////			
////			throw new CustomerException("customer id not valid!!!");
////		}
////		
////		FoodCart fc=new FoodCart();
////		fc.getItemList().add(item.get());
////		fc.setCustomer(cus.get());
//
//		FoodCart cart = cus.getFoodCart();
//
//		cart.getItemList().add(item);
//
//		return foodCartDao.save(cart);
//
//	}

	@Override
	public FoodCart addCart(FoodCart cart) {

		return foodCartDao.save(cart);
	}


	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity) throws ItemException, CartException {

//		Item item = itemDao.findById(itemId).orElseThrow(()->
//		
//		new ItemException("Item id not valid!!!")
//				
//				);

		foodCartDao.findById(cart.getCartId()).orElseThrow(() ->

		new CartException("cart is not found!!")

		);

		List<Item> items = cart.getItemList();

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
