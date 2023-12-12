package com.rest.service;

import com.rest.dto.OrderDTO;
import com.rest.exception.CustomRestaurantException;

public interface OrderService {
	String placeOrder(OrderDTO dto)throws CustomRestaurantException;
	String updateOrder(int orderId,int newQuantity)throws CustomRestaurantException;
	String cancelOrder(int orderId)throws CustomRestaurantException;
}
