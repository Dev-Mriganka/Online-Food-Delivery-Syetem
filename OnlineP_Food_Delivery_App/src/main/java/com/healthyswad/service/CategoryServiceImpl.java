package com.healthyswad.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Item;
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

	//Add Category -- tested
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
		
		cats.add(category);
		
		return categoryRepo.save(category);
		
	}
	
	
	//Update Category -- tested
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
				
				cat.setCategoryName(category.getCategoryName());
				
				return categoryRepo.save(category);
				
			}
		}	
		
		throw new CategoryException("Category Does Not Exist");
		
	}

	
	//Remove Category -- tested
	@Override
	public String removeCategory(Integer categoryId, String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		Set<Category> cats = restaurant.getCategories();
		
		for(Category cat: cats) {
			if(cat.getCategoryId() == categoryId) {
				
				categoryRepo.deleteById(categoryId);
				List<Item> items = cat.getItems();
				for(Item i: items){
					i.setCategory(null);
				}
				cat.setItems(null);
				cats.remove(cat);
				categoryRepo.delete(cat);
				return "Category Successfully Deleted..";
			}
		}
		
		throw new CategoryException("Category Does Not Exist");
	}

	
	//View Category  -- tested
	@Override
	public Category viewCategory(Integer categoryId, String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Category opt = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category Not Exist "));
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		if(restaurant.getCategories().contains(opt))
		
			return opt;
		
		throw new RestaurantException("Wrong catagory id for your restaurant..");
		
	}

	
	//View All Category  -- tested 
	@Override
	public Set<Category> viewAllCategoryByRestaurant(String key) throws CategoryException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);
		
		if(curr == null) throw new RestaurantException("No restaurant Logged in with this key..");
		
		if(curr.getRole().equalsIgnoreCase("customer")) throw new RestaurantException("You are not authorized..");
		
		Restaurant restaurant = restaurantRepo.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));
		
		return restaurant.getCategories();
		
	}
	
	
	//View All Category  -- tested
	@Override
	public Set<Category> viewAllCategoryByCustomer(Integer restaurantid) throws CategoryException, RestaurantException {
			
			Restaurant restaurant = restaurantRepo.findById(restaurantid)
					.orElseThrow(() -> new RestaurantException(""));
			
			return restaurant.getCategories();
			
		}


	@Override
	public Category viewCategoryByCustomer(Integer categoryId)
			throws CategoryException, RestaurantException {
		
		Category opt = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category Not Exist "));
		
		return opt;
		
	}
	
}
