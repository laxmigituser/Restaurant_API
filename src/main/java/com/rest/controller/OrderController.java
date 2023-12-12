package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.ItemDTO;
import com.rest.dto.OrderDTO;
import com.rest.dto.RestaurantDTO;
import com.rest.exception.CustomRestaurantException;
import com.rest.service.OrderService;
import com.rest.service.RestaurantService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


@RestController
@Validated
@RequestMapping("/order")
public class OrderController {
//	@Autowired
//	private RestaurantService restaurantservice;
	@Autowired
	private OrderService orderservice;

	
	@PostMapping("/createorder")
	ResponseEntity<String> placeOrder(@RequestBody @Valid OrderDTO orderDTO) throws CustomRestaurantException {
		String placeOrder = this.orderservice.placeOrder(orderDTO);
		return new ResponseEntity<String>(placeOrder, HttpStatus.OK);
	}
	@PutMapping("/updateorder/{orderId}/{quantityNew}")
	ResponseEntity<String> updateOrder(@PathVariable int orderId,@PathVariable @Min(value = 1,message = "MINIMUM REQUIRED VALUE IS 1") int quantityNew) throws CustomRestaurantException {
		String updateOrder = this.orderservice.updateOrder(orderId,quantityNew);
		return new ResponseEntity<String>(updateOrder, HttpStatus.OK);
	}

	@DeleteMapping("/cancelorder/{orderId}")
	ResponseEntity<String> cancelOrder(@PathVariable int orderId) throws CustomRestaurantException {
	String updateOrder = this.orderservice.cancelOrder(orderId);
	return new ResponseEntity<String>(updateOrder, HttpStatus.OK);
	}








}
