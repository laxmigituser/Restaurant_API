package com.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Optional<Order> findByItemName(String itemName);
	Optional<Order> findByRestaurantName(String restaurantName);
	List<Order> findByStatus(String status);
}
