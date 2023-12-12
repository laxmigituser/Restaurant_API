package com.rest.dto;

import org.aspectj.lang.annotation.RequiredTypes;

import com.rest.entity.Item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
/*
 * This DTO will be used to receive value from user
 */
public class ItemDTO {
	
	//private int itemId;
	@NotBlank@NotNull@NotEmpty
	private String itemName;
	
	@Min(value = 100)
	private int price;

	// private List<Restaurant> restaurants=new ArrayList<>() ;
	
	public static ItemDTO entityToDTO(Item item) {
		ItemDTO dto = new ItemDTO();
		//dto.setItemId(item.getItemId());
		dto.setItemName(item.getItemName());
		dto.setPrice(item.getPrice());
		return dto;
	}
	public static Item dtoToEntity(ItemDTO dto) {
		Item item = new Item();
		//item.setItemId(dto.getItemId());
		item.setItemName(dto.getItemName());
		item.setPrice(dto.getPrice());
		return item;
	}
//	public int getItemId() {
//		return itemId;
//	}
//
//	public void setItemId(int itemId) {
//		this.itemId = itemId;
//	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public List<Restaurant> getRestaurants() {
//		return restaurants;
//	}
//
//	public void setRestaurants(List<Restaurant> restaurants) {
//		this.restaurants = restaurants;
//	}

}
