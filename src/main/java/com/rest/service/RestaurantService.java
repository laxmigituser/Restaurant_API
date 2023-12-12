package com.rest.service;

import java.util.List;

import com.rest.dto.ItemDTO;
import com.rest.dto.OrderDTO;
import com.rest.dto.RestaurantDTO;
import com.rest.exception.CustomRestaurantException;

public interface RestaurantService {
	RestaurantDTO addRestaurant(RestaurantDTO dto)throws CustomRestaurantException;
	RestaurantDTO addItemsToRestaurant(String restaurantName,List<ItemDTO> itemDTO)throws CustomRestaurantException;
	RestaurantDTO removeItemFromRestaurant(String restaurantName,String itemName)throws CustomRestaurantException;
	RestaurantDTO getRestaurantInfo(String restaurantName)throws CustomRestaurantException;
	RestaurantDTO deleteRestaurant(String restaurantName)throws CustomRestaurantException;
	List<RestaurantDTO> searchRestaurantsWithItemname(String itemName)throws CustomRestaurantException;

}
