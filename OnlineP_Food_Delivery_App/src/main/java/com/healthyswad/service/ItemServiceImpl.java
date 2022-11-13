package com.healthyswad.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.healthyswad.dto.ItemDTO;
import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.exception.CategoryException;

import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CategoryRepo;
import com.healthyswad.repository.ItemRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private RestaurantRepo rr;

	@Autowired
	private SessionRepo sessionrepo;

	@Autowired
	private CategoryRepo categoryrepo;

	// Add Item -- tested
	@Override
	public Item addItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized..");

		Restaurant restaurant = rr.findById(curr.getUserId())
				.orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		Item item = new Item();
		item.setItemName(itemdto.getItemName());
		item.setDescription(itemdto.getDescription());
		item.setCost(itemdto.getCost());
		item.setImageUrl(itemdto.getImageUrl());
		item.setCategory(null);
		item.setRestaurant(restaurant);

		for (Item itm : items) {

			if (itm.equals(item))
				throw new ItemException("Item is already Present");

		}

		items.add(item);

		restaurant.setItemList(items);

		return itemRepo.save(item);

	}

	// Update Item -- tested
	@Override
	public Item updateItem(ItemDTO itemdto, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized..");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		Item item = new Item();
		item.setItemId(itemdto.getItemId());
		item.setItemName(itemdto.getItemName());
		item.setDescription(itemdto.getDescription());
		item.setCost(itemdto.getCost());
		item.setImageUrl(itemdto.getImageUrl());

		for (Item itm : items) {

			if (itm.getItemId() == itemdto.getItemId()) {

				itm.setItemName(itemdto.getItemName());
				itm.setDescription(itemdto.getDescription());
				itm.setCost(itemdto.getCost());
				itm.setImageUrl(itemdto.getImageUrl());

				return itemRepo.save(itm);
			}

		}

		throw new ItemException("Item is not Present");

	}

	// View Item -- tested
	@Override
	public ItemDTO viewItem(Integer itemId) throws ItemException {

		Item item = itemRepo.findById(itemId).orElseThrow(() -> new ItemException("Item does not exist with this id"));

		ItemDTO idto = new ItemDTO();
		idto.setItemId(item.getItemId());
		idto.setItemName(item.getItemName());
		idto.setCost(item.getCost());
		idto.setDescription(item.getDescription());
		idto.setImageUrl(item.getImageUrl());

		RestaurantDTO rdto = new RestaurantDTO();

		rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
		rdto.setContactNumber(item.getRestaurant().getContactNumber());
		rdto.setAddress(item.getRestaurant().getAddress());

		idto.setRestDTO(rdto);

		return idto;

	}

	// Remove Item -- tested
	@Override
	public Item removeItem(Integer itemId, String key) throws ItemException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized..");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		for (Item itm : items) {

			if (itm.getItemId() == itemId) {

				items.remove(itm);

				itemRepo.delete(itm);

				rr.save(restaurant);

				return itm;

			}

		}

		throw new ItemException("Item does not exist with this id");

	}

	// View Item By Category -- tested
	@Override
	public List<ItemDTO> viewAllItemsByCategory(Integer categoryId) throws CategoryException {

		Category cat = categoryrepo.findById(categoryId)
				.orElseThrow(() -> new CategoryException("Category Does Not Exist"));

		List<Item> items = cat.getItems();

		List<ItemDTO> idtos = new ArrayList<>();

		for (Item item : items) {

			ItemDTO idto = new ItemDTO();
			idto.setItemId(item.getItemId());
			idto.setItemName(item.getItemName());
			idto.setCost(item.getCost());
			idto.setDescription(item.getDescription());
			idto.setImageUrl(item.getImageUrl());

			RestaurantDTO rdto = new RestaurantDTO();
			rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
			rdto.setContactNumber(item.getRestaurant().getContactNumber());
			rdto.setAddress(item.getRestaurant().getAddress());

			idto.setRestDTO(rdto);

			idtos.add(idto);
		}

		return idtos;

	}

	// View All Item By Restaurant -- tested
	@Override
	public List<ItemDTO> viewAllItemsByRestaurant(Integer restaurantId) throws RestaurantException {

		Restaurant restaurant = rr.findById(restaurantId)
				.orElseThrow(() -> new RestaurantException("Restaurant Not Found..."));

		List<Item> items = restaurant.getItemList();

		List<ItemDTO> idtos = new ArrayList<>();

		for (Item item : items) {

			ItemDTO idto = new ItemDTO();
			idto.setItemId(item.getItemId());
			idto.setItemName(item.getItemName());
			idto.setCost(item.getCost());
			idto.setDescription(item.getDescription());
			idto.setImageUrl(item.getImageUrl());

			RestaurantDTO rdto = new RestaurantDTO();

			rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
			rdto.setContactNumber(item.getRestaurant().getContactNumber());
			rdto.setAddress(item.getRestaurant().getAddress());

			idto.setRestDTO(rdto);

			idtos.add(idto);
		}

		return idtos;

	}

	// Search Item By Name -- tested
	@Override
	public List<ItemDTO> viewAllItemsByName(String name) throws ItemException {

		List<Item> items = itemRepo.findByItemNameContaining(name);

		System.out.println(items);

		List<ItemDTO> idtos = new ArrayList<>();

		for (Item item : items) {

			ItemDTO idto = new ItemDTO();
			idto.setItemId(item.getItemId());
			idto.setItemName(item.getItemName());
			idto.setCost(item.getCost());
			idto.setDescription(item.getDescription());
			idto.setImageUrl(item.getImageUrl());

			RestaurantDTO rdto = new RestaurantDTO();

			rdto.setRestaurantName(item.getRestaurant().getRestaurantName());
			rdto.setContactNumber(item.getRestaurant().getContactNumber());
			rdto.setAddress(item.getRestaurant().getAddress());

			idto.setRestDTO(rdto);

			idtos.add(idto);
		}

		return idtos;
	}

	// Add Item to a Category -- tested
	@Override
	public Item addItemToCategoryByName(Integer itemId, String categoryName, String key)
			throws ItemException, CategoryException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No restaurant Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("You are not authorized..");

		Restaurant restaurant = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException(""));

		List<Item> items = restaurant.getItemList();

		Item item = null;

		for (Item itm : items) {

			if (itm.getItemId() == itemId) {

				item = itm;
				break;

			}

		}

		if (item == null)
			throw new ItemException("Item does not exist with this id");

		Set<Category> cats = restaurant.getCategories();

		for (Category cat : cats) {
			if (cat.getCategoryName().equalsIgnoreCase(categoryName)) {

				cat.getItems().add(item);

				item.setCategory(cat);

				categoryrepo.save(cat);
				itemRepo.save(item);
				rr.save(restaurant);

				return item;
			}
		}

		Category cat = new Category();
		cat.setItems(new ArrayList<>());
		cat.setCategoryName(categoryName);

		cats.add(cat);

		cat.getItems().add(item);
		categoryrepo.save(cat);

		item.setCategory(cat);

		itemRepo.save(item);
		rr.save(restaurant);

		return item;

	}

}
