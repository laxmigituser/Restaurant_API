package com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.dto.ItemDTO;
import com.rest.entity.Item;
import com.rest.entity.Restaurant;
import com.rest.exception.CustomRestaurantException;
import com.rest.repository.ItemRepository;
import com.rest.repository.RestaurantRepository;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public ItemDTO addItem(ItemDTO dto) throws CustomRestaurantException  {
		Item item = ItemDTO.dtoToEntity(dto);
		Optional<Item> items = itemRepository.findByItemNameIgnoreCase(dto.getItemName());
		if(items.isEmpty()) {
			Item savedItem = itemRepository.save(item);
			return ItemDTO.entityToDTO(savedItem);
		}
		throw new CustomRestaurantException("Item already exist!!!");
			//return null;
		
		
	}
	@Override
	public List<ItemDTO> searchItemsWithRestaurantname(String restaurantName) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(restaurantName);
		if(restaurant.isEmpty()) {
			throw new CustomRestaurantException("NO RESTAURANT FOUND WITH THE NAME ENTERED");
		}
		List<ItemDTO> itemList=new ArrayList<>();
		List<Item> items=restaurant.get().getItems();
		for(Item m:items) {
			itemList.add(new ItemDTO().entityToDTO(m));
			
		}		
		return itemList;
	}
	@Override
	public ItemDTO updateItemname(String oldName, String newName) throws CustomRestaurantException {
		Optional<Item> item = itemRepository.findByItemNameIgnoreCase(oldName);
		if(item.equals(null)) {
			throw new CustomRestaurantException("Item not found with given name");
		}
		item.get().setItemName(newName);
		itemRepository.save(item.get());
		return ItemDTO.entityToDTO(item.get());
	}

	@Override
	public ItemDTO updateItemPrice(String itemname, int price) throws CustomRestaurantException {
		Optional<Item> item = itemRepository.findByItemNameIgnoreCase(itemname);
		if(item.isPresent()) {
			item.get().setPrice(price);
			itemRepository.save(item.get());
		}else {
			throw new CustomRestaurantException("ITEM NOT FOUND");
		}		
		return ItemDTO.entityToDTO(item.get());
	}

	@Override
	public void deleteItem(String itemName) throws CustomRestaurantException {
		Optional<Item> item = itemRepository.findByItemNameIgnoreCase(itemName);
		//ItemDTO dto=new ItemDTO();
		if(item.isPresent()) {
			//dto=ItemDTO.entityToDTO(item.get());
			//remove
			itemRepository.deleteById(item.get().getItemId());
		}else {
			throw new CustomRestaurantException("ITEM NOT FOUND");
		}
		//return dto;
	}
}
