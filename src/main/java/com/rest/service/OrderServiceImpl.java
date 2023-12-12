package com.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.dto.OrderDTO;
import com.rest.entity.Item;
import com.rest.entity.Order;
import com.rest.entity.Restaurant;
import com.rest.exception.CustomRestaurantException;
import com.rest.repository.ItemRepository;
import com.rest.repository.OrderRepository;
import com.rest.repository.RestaurantRepository;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	
	@Override
	public String placeOrder(OrderDTO dto) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(dto.getRestaurantName());
		if(restaurant.isEmpty()) {
			throw new CustomRestaurantException("RESTAURANT NOT FOUND!!");
		}
		List<Item> itemList=restaurant.get().getItems();
		for(Item item:itemList) {
			if(item.getItemName().equalsIgnoreCase(dto.getItemName())) {
				int total=dto.getQuantity()*item.getPrice();
				Order order=new Order();
				order.setRestaurantname(restaurant.get().getRestaurantName());
				order.setItemname(item.getItemName());
				order.setQuantity(dto.getQuantity());
				order.setAmount(total);
				order.setStatus("CONFIRMED");
				orderRepository.save(order);
				return restaurant.get().getRestaurantName()+" SUCCESSFULLY ORDERED";
			}
		}
		throw new CustomRestaurantException("ORDER ITEM IS INVALID!!");
	}
	

	@Override
	public String updateOrder(int orderId, int newQuantity)throws CustomRestaurantException  {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isEmpty()) {
			throw new CustomRestaurantException("ORDER NOT FOUND");			
		}
		if(order.get().getStatus().equals("CANCELLED")) {
			throw new CustomRestaurantException("ORDER CANCELLED");
		}
		Item item = itemRepository.findByItemNameIgnoreCase(order.get().getItemname()).get();
		order.get().setQuantity(newQuantity);
		int total=newQuantity*item.getPrice();
		order.get().setAmount(total);
		orderRepository.save(order.get());
		return "UPDATE SUCCESS";
	}

	@Override	
	public String cancelOrder(int orderId) throws CustomRestaurantException  {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isEmpty()) {
			throw new CustomRestaurantException("ORDER NOT FOUND");			
		}
		if(order.get().getStatus().equals("CANCELLED")) {
			throw new CustomRestaurantException("ORDER ALREADY CANCELLED");
		}
		order.get().setStatus("CANCELLED");	
		orderRepository.save(order.get());
		return "CANCEL SUCCESS";		
	}

	

}
