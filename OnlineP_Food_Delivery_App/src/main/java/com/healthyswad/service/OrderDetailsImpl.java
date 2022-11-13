package com.healthyswad.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.dto.CustomerResOrderDTO;
import com.healthyswad.dto.OrderDTO;
import com.healthyswad.dto.RestOrderDto;
import com.healthyswad.dto.RestSimpleDTO;
import com.healthyswad.exception.AddressException;
import com.healthyswad.exception.CartException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Address;
import com.healthyswad.model.CurrentUserSession;
import com.healthyswad.model.Customer;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.FoodCartItems;
import com.healthyswad.model.Item;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.OrderItems;
import com.healthyswad.model.Restaurant;
import com.healthyswad.repository.CustomerRepo;
import com.healthyswad.repository.FoodCartDao;
import com.healthyswad.repository.FoodCartItemRepo;
import com.healthyswad.repository.ItemRepo;
import com.healthyswad.repository.OrderDetailsRepo;
import com.healthyswad.repository.OrderItemsRepo;
import com.healthyswad.repository.RestaurantRepo;
import com.healthyswad.repository.SessionRepo;

@Service
public class OrderDetailsImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepo odr;
	
	@Autowired
	private ItemRepo ir;
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private RestaurantRepo rr;

	@Autowired
	private SessionRepo sessionrepo;
	
	@Autowired
	private FoodCartDao fd;
	
	@Autowired
	private FoodCartItemRepo fcir;

	@Autowired
	private OrderItemsRepo oir;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//add Order Details -- Tested
	@Override
	public OrderDetails addDetails(Integer addressId, String key)
			throws OrderDetailsException, CustomerException, AddressException, CartException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer..");

		Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));
		
		System.out.println("hi");
		
//		System.out.println(c);
		
		Set<Address> address = c.getAddresses();

		for (Address ad : address) {

			if (ad.getAddressId() == addressId) {

				FoodCart fc = c.getFoodCart();
				
//				System.out.println(fc);

				if (fc.getItemList().size() == 0) {

					throw new CartException("Cart is Empty...");

				}

				OrderDetails od = new OrderDetails();

				List<OrderItems> odItems = new ArrayList<>();

				List<FoodCartItems> fcItems = fc.getItemList();

				od.setCustomer(c);
				od.setOrderDate(LocalDateTime.now());
				od.setOrderAddress(ad);
				od.setRestaurant(fc.getItemList().get(0).getItem().getRestaurant());
				od.setOrderStatus(false);
				for (FoodCartItems i : fcItems) {
//					System.out.println(i);
					OrderItems odi = new OrderItems();
					odi.setItem(i.getItem());
					odi.setQuantity(i.getQuantity());
					odi.setOd(od);
					odItems.add(odi);
					
//					oir.save(odi);
					i.setFc(null);

				}
				
				odr.save(od);
				fcItems.clear();
				fd.save(fc);
				return od;

			}

		}

		throw new AddressException("Address Id is wrong...");

	}

	
	//update Order item -- Tested
	@Override
	public OrderDetails updateItem(Integer itemId, Integer orderId, String key)
			throws OrderDetailsException, CustomerException, CartException, ItemException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer..");

		cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));

		OrderDetails order = odr.findById(orderId)
				.orElseThrow(() -> new OrderDetailsException("Order Id not in Database.."));
		
		Item item = ir.findById(itemId)
				.orElseThrow(() -> new ItemException("Order Id not in Database.."));
		
		List<OrderItems> orderItems = order.getItemList();
		
		if(orderItems.get(0).getItem().getRestaurant().getRestaurantId() == item.getRestaurant().getRestaurantId()) {
			
			for(OrderItems i: orderItems) {
				
				if(i.getItem().getItemId() == itemId) {
					
					i.setQuantity(i.getQuantity()+1);
					return odr.save(order);
					
				}
					
			}
			OrderItems odi = new OrderItems();
			
			odi.setItem(item);
			odi.setOd(order);
			odi.setQuantity(1);
			
			return odr.save(order);
			
		}else {
			
			throw new OrderDetailsException("item is from different restaurant..");
			
		}
		
	}

	
	//update Order address -- Tested
	@Override
	public OrderDetails updateAddress(Integer addressId, Integer orderId, String key)
			throws OrderDetailsException, CustomerException, CartException, AddressException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer..");

		Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));

		OrderDetails order = odr.findById(orderId)
				.orElseThrow(() -> new OrderDetailsException("Order Id not in Database.."));

		if (order.getCustomer().getCustomerId() == c.getCustomerId()) {

			Set<Address> address = c.getAddresses();

			for (Address ad : address) {

				if (ad.getAddressId() == addressId) {

					if (order.getOrderStatus()) {

						throw new OrderDetailsException("Order Is processed not able to update. Contact Customer care");

					} else {

						order.setOrderAddress(ad);
						return odr.save(order);

					}

				}
			}

			throw new AddressException("Address Id is wrong...");

		}

		throw new OrderDetailsException("Order Id mis match..");

	}

	
	//cancel Order -- Tested
	@Override
	public String cancelOrder(Integer orderId, String key) throws OrderDetailsException, CustomerException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer..");

		Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));

		OrderDetails order = odr.findById(orderId)
				.orElseThrow(() -> new OrderDetailsException("Order Id not in Database.."));

		if (order.getCustomer().getCustomerId() == c.getCustomerId()) {

			if (order.getOrderStatus()) {

				throw new OrderDetailsException("Order Is processed not able to cancel. please contact Customer care");

			} else {

				odr.delete(order);
				return "Order Canceled Successfully...";

			}

		}

		throw new OrderDetailsException("Order Id mis match..");
	}

	
	//view Order -- Tested
	@Override
	public OrderDTO viewOrder(Integer orderId, String key) throws OrderDetailsException, CustomerException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("restaurant"))
			throw new CustomerException("Log in as a customer..");

		Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));
		
		OrderDetails order = odr.findById(orderId)
				.orElseThrow(() -> new OrderDetailsException("Order Id not in Database.."));
		
		if (order.getCustomer().getCustomerId() == c.getCustomerId()) {
			
			OrderDTO odrDTO = this.modelMapper.map(order, OrderDTO.class);
			
			RestSimpleDTO rest = new RestSimpleDTO();
			
			rest.setRestaurantId(orderId);
			rest.setContactNumber(order.getRestaurant().getContactNumber());
			rest.setRestaurantName(order.getRestaurant().getRestaurantName());
			

			return odrDTO;
			
		}

		throw new OrderDetailsException("Order Id mis match..");
		
	}

	
	//view All Orders -- Tested
	@Override
	public List<OrderDTO> viewAllOrders(String key) throws OrderDetailsException, CustomerException, RestaurantException {

		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new CustomerException("No customer Logged in with this key..");

		List<OrderDetails> odDetails;
		
		List<OrderDTO> odDTO = new ArrayList<>();;
		
		if (curr.getRole().equalsIgnoreCase("customer")) {

			Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));
			
			odDetails = c.getOrders();
			
		}else {
			
			Restaurant r = rr.findById(curr.getUserId())
					.orElseThrow(() -> new RestaurantException("You are not authorized.."));
			
			odDetails = r.getOrderLists();
			
		}
		
		for(OrderDetails order: odDetails) {
			
			OrderDTO odrDTO = this.modelMapper.map(order, OrderDTO.class);
			RestSimpleDTO rest = new RestSimpleDTO();
			
			rest.setRestaurantId(order.getRestaurant().getRestaurantId());
			rest.setContactNumber(order.getRestaurant().getContactNumber());
			rest.setRestaurantName(order.getRestaurant().getRestaurantName());
			
			odDTO.add(odrDTO);
			
		}
			
		if(odDTO.size() == 0)
			throw new OrderDetailsException("Order Id mis match..");
		
		return odDTO;
		
	}
	
	
	
	//view All Orders -- Tested
	@Override
	public List<RestOrderDto> viewAllOrdersRestaurant(String key) throws OrderDetailsException, CustomerException, RestaurantException {

			CurrentUserSession curr = sessionrepo.findByUuid(key);

			if (curr == null)
				throw new CustomerException("No customer Logged in with this key..");

			List<OrderDetails> odDetails;
			
			List<RestOrderDto> odDTO = new ArrayList<>();;
			
			if (curr.getRole().equalsIgnoreCase("customer")) {

				Customer c = cr.findById(curr.getUserId()).orElseThrow(() -> new CustomerException("You are not authorized.."));
				
				odDetails = c.getOrders();
				
			}else {
				
				Restaurant r = rr.findById(curr.getUserId())
						.orElseThrow(() -> new RestaurantException("You are not authorized.."));
				
				odDetails = r.getOrderLists();
				
			}
			
			for(OrderDetails order: odDetails) {
				
				RestOrderDto restOrderDto = this.modelMapper.map(order, RestOrderDto.class);
				restOrderDto.setCustomer(this.modelMapper.map(order.getCustomer(), CustomerResOrderDTO.class));
				
				odDTO.add(restOrderDto);
				
			}
				
			if(odDTO.size() == 0)
				throw new OrderDetailsException("Order Id mis match..");
			
			return odDTO;
			
		}


	//updated Status -- Tested
	@Override
	public OrderDTO updateStatus(Integer orderId, String key) throws OrderDetailsException, RestaurantException {
		
		CurrentUserSession curr = sessionrepo.findByUuid(key);

		if (curr == null)
			throw new RestaurantException("No customer Logged in with this key..");

		if (curr.getRole().equalsIgnoreCase("customer"))
			throw new RestaurantException("Log in as a restaurant..");

		Restaurant res = rr.findById(curr.getUserId()).orElseThrow(() -> new RestaurantException("You are not authorized.."));

		OrderDetails order = odr.findById(orderId)
				.orElseThrow(() -> new OrderDetailsException("Order Id not in Database.."));
		
		
		if(order.getRestaurant().getRestaurantId() == res.getRestaurantId()) {
			
			if(order.getOrderStatus()) {			
				
				throw new OrderDetailsException("item is already approaved..");
				
			}else {
			
				order.setOrderStatus(true);
				odr.save(order);
				
				OrderDTO odrDTO = this.modelMapper.map(order, OrderDTO.class);
				
				RestSimpleDTO rest = new RestSimpleDTO();
				
				rest.setRestaurantId(orderId);
				rest.setContactNumber(order.getRestaurant().getContactNumber());
				rest.setRestaurantName(order.getRestaurant().getRestaurantName());
				
				return odrDTO;
			}
			
		}else {
			
			throw new OrderDetailsException("item is from different restaurant..");
			
		}
		
	}

}
