package com.healthyswad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.ItemReo;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemReo itemRepo;
	
	
	

	@Override
	public Item addItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		
		Item itm = this.itemRepo.findByItemName(item.getItemName()); 
		
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
		return null;
	}

	@Override
	public Item removeItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItemsByCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItemsByRestaurant(Restaurant restaurant) throws RestaurantExcaption {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> viewAllItemsByName(String name) throws ItemException {
		// TODO Auto-generated method stub
		return null;
	}

}
