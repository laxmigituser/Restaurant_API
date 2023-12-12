package com.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int orderId;

private String itemName;

private String restaurantName;

private int quantity;

private int amount;

private String status;

public int getOrderId() {
	return orderId;
}

public void setOrderId(int orderId) {
	this.orderId = orderId;
}


public String getItemname() {
	return itemName;
}

public void setItemname(String itemName) {
	this.itemName = itemName;
}

public String getRestaurantname() {
	return restaurantName;
}

public void setRestaurantname(String restaurantName) {
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
