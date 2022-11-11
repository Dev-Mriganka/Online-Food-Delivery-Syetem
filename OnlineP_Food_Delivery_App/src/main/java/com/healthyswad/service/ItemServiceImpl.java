package com.healthyswad.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;



import com.healthyswad.exception.CategoryException;

import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.ItemRepo;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepo itemRepo;
	
	
	

	@Override
	public Item addItem(Item item) throws ItemException {

		
		Item itm = itemRepo.findByItemName(item.getItemName()); 
		
		if(itm == null) {
			itm = itemRepo.save(item);
		}else {
			throw new ItemException("Item is allready Present");
		}
		
		return itm;

	}

	@Override
	public Item updateItem(Item item) throws ItemException {
		

		
		Item itm = itemRepo.findByItemName(item.getItemName());
		if(itm != null) {
			itm = itemRepo.save(item);
		}else {
			throw new ItemException("Item is not Present");
		}
		
		return itm;
	}

	@Override
	public Item viewItem(Item item) throws ItemException {
		// TODO Auto-generated method stub

		
		Optional<Item> itm = itemRepo.findById(item.getItemId());
		
		if(itm.isPresent()) {
			Item itmm = itm.get();
			return itmm;
		}else {
			throw new ItemException("Item not Exist");
		}
		

	}


	public Item removeItem(Item item) throws ItemException {
		
		Optional<Item> itm=itemRepo.findById(item.getItemId());
		
		if(itm.isPresent()) {
			  Item itmm = itm.get();
			  
			  itemRepo.delete(itmm);
			  return itmm;
		}else {
			throw new ItemException("Item does not exist with this id");
		}
		
		
	}

	@Override
	public List<ItemDTO> viewAllItemsByCategory(Category category) throws CategoryException {
		
		   List<Item> itm = itemRepo.findByCategory(category);
		   
		   if(itm.size()>0) {
			   return itm;
		   }else {
			   throw new CategoryException("This category is not found");
		   }
		   
	}

	@Override
	public List<ItemDTO> viewAllItemsByRestaurant(Restaurant restaurant) throws RestaurantExcaption, RestaurantExcaption {
		
		Optional<Item> res = itemRepo.findById(restaurant.getRestaurantId());
		
		if(res.isPresent()) {
			
			
			
		}else {
			throw new RestaurantExcaption("Restaurant is not Exist");		
		
		}
		
		
		
		return null;
	}

	@Override
	public List<Item> viewAllItemsByName(String name) throws ItemException {
		
		Item itms = itemRepo.findByItemName(name);
		
		
		List<Item> it = new ArrayList<>();
		
		

		if(itms != null) {
			it.add(itms);
		}else {
			throw new ItemException("With this name no items available");
		}
		
		return it;
	}

}
