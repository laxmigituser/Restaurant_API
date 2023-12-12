package com.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.rest.entity.Item;
import com.rest.entity.Restaurant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RestaurantDTO {
	
	//private int restaurantId;	
	@NotBlank(message = "RESTAURANT NAME REQUIRED")
	private String restaurantName;	
	@NotBlank(message = "RESTAURANT LOCATION REQUIRED")
	private String location;
	@Pattern(regexp = "[0-9]{10}",message = "MOBILE NUMBER SHOULD 10 DIGIT")
	private String phoneNo;
	private List<ItemDTO> items=new ArrayList<>();
	
	public static RestaurantDTO entityToDTO(Restaurant res) {
		RestaurantDTO dto=new RestaurantDTO();
		dto.setLocation(res.getLocation());
		dto.setPhoneNo(res.getPhoneNo());
		dto.setRestaurantName(res.getRestaurantName());
		
		//item list
		List<Item> itemList=res.getItems();
		List<ItemDTO> dtoList=new ArrayList<>();
		for(Item it:itemList) {
			ItemDTO dtoo = ItemDTO.entityToDTO(it);
			dtoList.add(dtoo);
		}
		dto.setItems(dtoList);
		return dto;
	}
	public static Restaurant dtoToEntity(RestaurantDTO dto) {
		Restaurant res=new Restaurant();
		res.setLocation(dto.getLocation());
		res.setPhoneNo(dto.getPhoneNo());
		res.setRestaurantName(dto.getRestaurantName());
		//item list
				List<ItemDTO> dtoList=dto.getItems();
				List<Item> itemList=new ArrayList<>();
				for(ItemDTO dtoo:dtoList) {
					Item it = ItemDTO.dtoToEntity(dtoo);
					itemList.add(it);
				}
		res.setItems(itemList);
		return res;
	}
		
//		public int getRestaurantId() {
//			return restaurantId;
//		}
//		public void setRestaurantId(int restaurantId) {
//			this.restaurantId = restaurantId;
//		}
		public String getRestaurantName() {
			return restaurantName;
		}
		public void setRestaurantName(String restaurantName) {
			this.restaurantName = restaurantName;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public List<ItemDTO> getItems() {
			return items;
		}
		public void setItems(List<ItemDTO> items) {
			this.items = items;
		}
		
}
