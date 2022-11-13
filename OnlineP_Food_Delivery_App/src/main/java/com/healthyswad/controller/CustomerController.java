package com.healthyswad.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.dto.CustAddDto;
import com.healthyswad.dto.CustomerDto;
import com.healthyswad.dto.ItemDTO;
import com.healthyswad.dto.OrderDTO;
import com.healthyswad.dto.RestaurantDTO;
import com.healthyswad.dto.Viewprofile;
import com.healthyswad.exception.AddressException;
import com.healthyswad.exception.CartException;
import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.LoginException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.Category;
import com.healthyswad.model.Customer;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.service.CartService;
import com.healthyswad.service.CategoryService;
import com.healthyswad.service.CustomerService;
import com.healthyswad.service.ItemService;
import com.healthyswad.service.LoginService;
import com.healthyswad.service.OrderDetailsService;
import com.healthyswad.service.RestaurantService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService cs;

	@Autowired
	private CartService carts;

	@Autowired
	private ItemService its;

	@Autowired
	private RestaurantService rs;

	@Autowired
	private LoginService customerLogin;

	@Autowired
	private OrderDetailsService or;

	@Autowired
	private CategoryService cts;

	// --------Basic Customer operation----------//
	@PostMapping("/register") // --tested--
	public ResponseEntity<Customer> registerCustomerHandler(@Valid @RequestBody CustAddDto customer) throws CustomerException {

		Customer cust = cs.registerCustomer(customer);

		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}

	@PutMapping("/update") // --tested--
	public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody CustAddDto customer, String key)
			throws CustomerException {

		Customer cust = cs.updateCustomer(customer, key);

		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);

	}

	@DeleteMapping("/delete") // --tested--
	public ResponseEntity<String> deleteCustomer(@RequestParam Integer customerId, @RequestParam String key)
			throws CustomerException, LoginException {

		String cust = cs.deleteCustomer(customerId, key);
		customerLogin.logOutFromAccount(key);

		return new ResponseEntity<String>(cust, HttpStatus.ACCEPTED);

	}

	@GetMapping("/profile") // --tested--
	public ResponseEntity<Viewprofile> viewProfile(@RequestParam Integer customerId, @RequestParam String key)
			throws CustomerException {

		Viewprofile cust = cs.viewProfile(customerId, key);

		return new ResponseEntity<Viewprofile>(cust, HttpStatus.OK);
	}

	// --------Address operation---------//
	@PostMapping("/address/add") // --tested--
	public ResponseEntity<CustomerDto> addAddress(@RequestParam Integer customerId, @RequestBody Address add,
			@RequestParam String key) throws CustomerException, AddressException {
		CustomerDto cdto = cs.addAddress(customerId, add, key);

		return new ResponseEntity<CustomerDto>(cdto, HttpStatus.ACCEPTED);

	}

	@PutMapping("/address/edit") // --tested--
	public ResponseEntity<CustomerDto> editAddress(@RequestParam Integer customerId, @RequestBody Address add,
			@RequestParam String key) throws CustomerException, AddressException {
		CustomerDto cdto = cs.editAddress(customerId, add, key);

		return new ResponseEntity<CustomerDto>(cdto, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/address/delete") // --tested--
	public ResponseEntity<CustomerDto> deleteAddress(@RequestParam Integer customerId, @RequestParam Integer addId,
			@RequestParam String key) throws CustomerException, AddressException {
		CustomerDto cdto = cs.removeAddress(customerId, addId, key);

		return new ResponseEntity<CustomerDto>(cdto, HttpStatus.OK);
	}

	// --------Cart operation----------//
	@PutMapping("/cart/add") // --tested--
	public ResponseEntity<FoodCart> addItemToCartHandler(@RequestParam Integer itemId, @RequestParam String key)
			throws RestaurantException, ItemException {

		FoodCart fc = carts.addItemToCart(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.ACCEPTED);

	}

	@PutMapping("/cart/increase") // --tested--
	public ResponseEntity<FoodCart> increaseQuantityHandler(@RequestParam Integer itemId,
			@RequestParam Integer Quantity, @RequestParam String key)
			throws RestaurantException, ItemException, CartException {

		FoodCart fc = carts.increaseQuantity(itemId, Quantity, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@PutMapping("/cart/reduce") // --tested--
	public ResponseEntity<FoodCart> reduceQuantityHandler(@RequestParam Integer itemId, @RequestParam Integer Quantity,
			@RequestParam String key) throws RestaurantException, ItemException, CartException {

		FoodCart fc = carts.reduceQuantity(itemId, Quantity, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@DeleteMapping("/cart/remove") // --tested--
	public ResponseEntity<FoodCart> removeHandler(@RequestParam Integer itemId, @RequestParam String key)
			throws RestaurantException, ItemException, CartException {

		FoodCart fc = carts.removeItem(itemId, key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}

	@DeleteMapping("/cart/clear") // --tested--
	public ResponseEntity<String> clearcartHandler(@RequestParam String key)
			throws RestaurantException, CartException {

		String fc = carts.clearCart(key);

		return new ResponseEntity<String>(fc, HttpStatus.OK);
	}
	
	
	@GetMapping("/cart/view") // --tested--
	public ResponseEntity<FoodCart> viewCartHandler(@RequestParam String key)
			throws RestaurantException, CartException {

		FoodCart fc = carts.viewCart(key);

		return new ResponseEntity<FoodCart>(fc, HttpStatus.OK);
	}
	
	
	// --------Item operation----------//
	@GetMapping("/search/item") // --tested--
	public ResponseEntity<ItemDTO> viewItemHandller(@RequestParam Integer itemKey) throws ItemException {

		ItemDTO it = its.viewItem(itemKey);

		return new ResponseEntity<ItemDTO>(it, HttpStatus.OK);
	}

	@GetMapping("/search/item/restaurant") // --tested--
	public ResponseEntity<List<ItemDTO>> viewAllItemByRestaurantHandler(@RequestParam Integer restId)
			throws ItemException, RestaurantException {

		return new ResponseEntity<List<ItemDTO>>(its.viewAllItemsByRestaurant(restId), HttpStatus.OK);
	}

	@GetMapping("/search/item/category")
	public ResponseEntity<List<ItemDTO>> viewAllItemByCategoryHandller(@RequestParam Integer catId)
			throws CategoryException {

		return new ResponseEntity<List<ItemDTO>>(its.viewAllItemsByCategory(catId), HttpStatus.OK);
	}

	@GetMapping("/search/item/name") // --tested--
	public ResponseEntity<List<ItemDTO>> viewAllItemsByNameHandller(@RequestParam String name) throws ItemException {

		return new ResponseEntity<List<ItemDTO>>(its.viewAllItemsByName(name), HttpStatus.OK);
	}

	// --------Restaurant operation---------//
	@GetMapping("/search/restaurant") // --tested--
	public ResponseEntity<RestaurantDTO> viewRestaurant(@RequestParam Integer resId) throws RestaurantException {

		RestaurantDTO rdto = rs.viewRestaurant(resId);

		return new ResponseEntity<RestaurantDTO>(rdto, HttpStatus.OK);
	}

	@GetMapping("/search/restaurant/nearby") // --tested--
	public ResponseEntity<List<RestaurantDTO>> viewNearByRestaurant(@RequestParam String city)
			throws RestaurantException {

		List<RestaurantDTO> rdto = rs.viewNearByRestaurant(city);
		return new ResponseEntity<List<RestaurantDTO>>(rdto, HttpStatus.OK);

	}

	@GetMapping("/search/restaurant/item") // --tested--
	public ResponseEntity<Set<RestaurantDTO>> searchRestaurantByItemName(@RequestParam String name)
			throws ItemException, RestaurantException{

		Set<RestaurantDTO> rest = rs.viewRestaurantByItemName(name);

		return new ResponseEntity<Set<RestaurantDTO>>(rest, HttpStatus.OK);
	}

	// --------Order operation---------//
	@PostMapping("/order/add") // --tested--
	public ResponseEntity<OrderDetails> addDetailsHandller(@RequestParam Integer addressId, @RequestParam String key)
			throws OrderDetailsException, CustomerException, AddressException, CartException {

		System.out.println("hi");

		OrderDetails dt = or.addDetails(addressId, key);

		return new ResponseEntity<OrderDetails>(dt, HttpStatus.OK);

	}

	@PutMapping("/order/updateitem") // --tested--
	public ResponseEntity<OrderDetails> updataDetailsupdateItem(@RequestParam Integer itemId,
			@RequestParam Integer orderId, @RequestParam String key)
			throws OrderDetailsException, CustomerException, CartException, ItemException {

		OrderDetails dtt = or.updateItem(itemId, orderId, key);

		return new ResponseEntity<OrderDetails>(dtt, HttpStatus.OK);

	}

	@PutMapping("/order/updateaddress") // --tested--
	public ResponseEntity<OrderDetails> updateAddress(@RequestParam Integer addressId, @RequestParam Integer orderId,
			@RequestParam String key) throws OrderDetailsException, CustomerException, CartException, AddressException {

		OrderDetails dt = or.updateAddress(addressId, orderId, key);

		return new ResponseEntity<OrderDetails>(dt, HttpStatus.OK);

	}

	@DeleteMapping("/order/cancel") // --tested--
	public ResponseEntity<String> cancelOrder(@RequestParam Integer orderId, @RequestParam String key)
			throws OrderDetailsException, CustomerException {

		String str = or.cancelOrder(orderId, key);

		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@GetMapping("order/id") // --tested--
	public ResponseEntity<OrderDTO> viewOrder(@RequestParam Integer orderId, @RequestParam String key)
			throws OrderDetailsException, CustomerException, RestaurantException {

		OrderDTO dt = or.viewOrder(orderId, key);

		return new ResponseEntity<OrderDTO>(dt, HttpStatus.OK);
	}

	@GetMapping("/order/viewall") // --tested--
	public ResponseEntity<List<OrderDTO>> viewAllOrders(@RequestParam String key)
			throws OrderDetailsException, CustomerException, RestaurantException {

		List<OrderDTO> dt = or.viewAllOrders(key);

		return new ResponseEntity<List<OrderDTO>>(dt, HttpStatus.OK);
	}

//	@GetMapping("/category")
//	public ResponseEntity<Category> viewCategoryByCustomer(Integer categoryId)
//			throws CategoryException, RestaurantException {
//
//		return new ResponseEntity<Category>(cts.viewCategoryByCustomer(categoryId), HttpStatus.ACCEPTED);
//
//	}

	@GetMapping("/categories")
	public ResponseEntity<Set<Category>> viewAllCategoryByCustomer(Integer restaurantId)
			throws CategoryException, RestaurantException {

		return new ResponseEntity<Set<Category>>(cts.viewAllCategoryByCustomer(restaurantId), HttpStatus.ACCEPTED);

	}

}
