package com.healthyswad.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CategoryRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private RestaurantRepo restaurantRepo;

	@Override
	public Category addCategory(Category category, String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		


		

		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");

		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Set<Category> cats = restaurant.getCategories();
		
		for(Category cat: cats) {
			if(cat.getCategoryName().equalsIgnoreCase(category.getCategoryName())) {
				throw new CategoryException("Category is Already Exist in restaurant");
			}
		}	
		
		return categoryRepo.save(category);
		
	}
	

	@Override
	public Category updateCategory(Category category, String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Set<Category> cats = restaurant.getCategories();
		
		for(Category cat: cats) {
			if(cat.getCategoryId() == category.getCategoryId()) {
				
				return categoryRepo.save(category);
				
			}
		}	
		
		throw new CategoryException("Category Does Not Exist");
		
	}

	
	@Override
	public Category removeCategory(Category category, String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Set<Category> cats = restaurant.getCategories();
		
		for(Category cat: cats) {
			if(cat.getCategoryId() == category.getCategoryId()) {
				
				categoryRepo.delete(category);
				return category;
			}
		}
		
		throw new CategoryException("Category Does Not Exist");
	}

	
	@Override
	public Category viewCategory(Category category, String key) throws CategoryException, RestaurantException {
		
		Category opt = categoryRepo.findById(category.getCategoryId())
				.orElseThrow(() -> new CategoryException("Category Not Exist "));
		
		return opt;
		
	}

	
	@Override
	public Set<Category> viewAllCategory(String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		return restaurant.getCategories();
		
	}

}
