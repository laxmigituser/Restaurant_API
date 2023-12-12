package com.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	Optional<Restaurant> findByRestaurantNameIgnoreCase(String resName);
	List<Restaurant> findByLocation(String location);
	Optional<Restaurant> findByPhoneNo(String phoneNo);

}
