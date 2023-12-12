package com.rest.dto;

import com.rest.entity.Order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class OrderDTO {
	
	private int orderId;
	private String itemName;
	private String restaurantName;
	@Min(value = 1,message = "MINIMUM REQUIRED VALUE IS 1")
	private int quantity;
	private int amount;
	private String status;
	
	/*
	@Pattern(regexp = ".*[\\S]+.*",message = "regex failed")
private String itemName;
private String restaurantName;
@Min(value = 1,message = "invalid quantity")
private int quantity;
*/
	public Order dtoToEntity(OrderDTO dto) {
		Order order =new Order();
		order.setOrderId(dto.getOrderId());
		order.setItemname(dto.getItemName());
		order.setRestaurantname(dto.getRestaurantName());
		order.setQuantity(dto.getQuantity());
		order.setAmount(dto.getAmount());
		order.setStatus(dto.getStatus());
		return order;
	}
	public OrderDTO entityToDTO(Order order) {
		OrderDTO dto =new OrderDTO();
		dto.setOrderId(order.getOrderId());
		dto.setItemName(order.getItemname());
		dto.setRestaurantName(order.getRestaurantname());
		dto.setQuantity(order.getQuantity());
		dto.setAmount(order.getAmount());
		dto.setStatus(order.getStatus());
		return dto;
	}
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	} 
	
}
