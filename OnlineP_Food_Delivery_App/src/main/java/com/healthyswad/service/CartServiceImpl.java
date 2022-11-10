package com.healthyswad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.FoodCart;
import com.healthyswad.repository.FoodCartDao;
import com.healthyswad.repository.ItemRepo;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private FoodCartDao foodCartDao;
	
	@Autowired
	private ItemRepo itemDao;
	
	
	
	@Override
	public FoodCart addItemToCart(Integer itemId, String key) throws CustomerException {
	
		
		return null;
	}

}
