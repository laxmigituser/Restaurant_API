package com.rest.service;

import java.util.List;
import java.util.Optional;

import com.rest.dto.ItemDTO;
import com.rest.exception.CustomRestaurantException;

public interface ItemService {
	List<ItemDTO> searchItemsWithRestaurantname(String itemName)throws CustomRestaurantException;
	ItemDTO addItem(ItemDTO dto) throws CustomRestaurantException;
	ItemDTO updateItemname(String oldName,String newName)throws CustomRestaurantException;
	ItemDTO updateItemPrice(String itemname,int price)throws CustomRestaurantException;
	void deleteItem(String itemName)throws CustomRestaurantException;

}
