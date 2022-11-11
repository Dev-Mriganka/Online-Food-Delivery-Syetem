package com.healthyswad.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		
		Category existedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
		
		if(existedCategory!=null) 
			throw new CategoryException("Category is Already Exist ");
		
		
		Set<Item> items = category.getItms();
		
		for(Item item : items) {
			item.setCategory(category);
		}
		
		return categoryRepo.save(category);
		
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		
		Category existedCategory = categoryRepo.findByCategoryName(category.getCategoryName());
		
		if(existedCategory==null)
			throw new CategoryException("Category Does Not Exist");
			
		
		
         Set<Item> items = category.getItms();
		
		for(Item item : items) {
			item.setCategory(category);
		}
		
		return categoryRepo.save(category);
	}

	@Override
	public Category removeCategory(Category category) throws CategoryException {
		Optional<Category> opt = categoryRepo.findById(category.getCategoryId());
		
		if(opt.isPresent()) {
			categoryRepo.delete(category);
			return category;
		}else
			throw new CategoryException("Category Does Not Exist ");
	}

	@Override
	public Category viewCategory(Category category) throws CategoryException {
		Optional<Category> opt = categoryRepo.findById(category.getCategoryId());
		
		if(opt.isPresent()) {
			return opt.get();
		}else
			throw new CategoryException("Category Not Exist ");
		
	}

	@Override
	public List<Category> viewAllCategory() throws CategoryException {
		
		List<Category> categories = categoryRepo.findAll();
		
		if(categories.isEmpty())
			throw new CategoryException("Categories Not Exist ");
		else
			return categories;
		
	}

}
