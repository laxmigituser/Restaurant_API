package com.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.rest.dto.ItemDTO;
import com.rest.dto.OrderDTO;
import com.rest.dto.RestaurantDTO;
import com.rest.entity.Item;
import com.rest.entity.Order;
import com.rest.entity.Restaurant;
import com.rest.exception.CustomRestaurantException;
import com.rest.repository.ItemRepository;
import com.rest.repository.OrderRepository;
import com.rest.repository.RestaurantRepository;

//import jakarta.transaction.Transactional;
@Service
//@PropertySource("classpath:ValidationMessages.properties")
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private ItemRepository itemRepository;
//	@Autowired
//	private OrderRepository orderRepository;
//	@Autowired
//	 private ModelMapper modelMapper;
	
	@Override
	public RestaurantDTO addRestaurant(RestaurantDTO dto) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.
				findByRestaurantNameIgnoreCase(dto.getRestaurantName());
		if(restaurant.isEmpty()) {
			List<ItemDTO> itemsListDTO = dto.getItems();
			if(itemsListDTO.size()>0) {
				Restaurant savedEntity = restaurantRepository.save(RestaurantDTO.dtoToEntity(dto));
				return RestaurantDTO.entityToDTO(savedEntity);

			}else {
				throw new CustomRestaurantException("ITEM INFO REQUIRED!!!");
			}
			
		}else {
			throw new CustomRestaurantException("RESTAURANT EXIST WITH GIVEN NAME");

		}
	}
	@Override
	public RestaurantDTO addItemsToRestaurant(String restaurantName, List<ItemDTO> itemDTO) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(restaurantName);
		if(restaurant.isEmpty()) {
			throw new CustomRestaurantException("Restaurant not found!!");
		}
		List<Item> list=new ArrayList<>();
		list.addAll(restaurant.get().getItems());
		List<Item> list1 = itemDTO.stream().map(s->ItemDTO.dtoToEntity(s)).collect(Collectors.toList());
		//list.add(ItemDTO.dtoToEntity(itemDTO));
		list.addAll(list1);
		restaurant.get().setItems(list);
		Restaurant save = restaurantRepository.save(restaurant.get());
		return RestaurantDTO.entityToDTO(save);
	}
	
	//@SuppressWarnings("unlikely-arg-type")
	@Override
	public RestaurantDTO removeItemFromRestaurant(String restaurantName, String itemName)
			throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(restaurantName);
		if(restaurant.isEmpty()) {
			throw new CustomRestaurantException("NO RESTAURANT FOUND WITH GIVEN NAME!!");
		}		
		List<Item> items = restaurant.get().getItems();
		//check if any items from user not exist
		if(items.contains(itemName)) {
			throw new CustomRestaurantException("PLEASE REVIEW ALL ITEM NAMES!!");
		}
		//remove item
		List<Item> collect = items.stream().filter(s->!itemName.equalsIgnoreCase(s.getItemName())).collect(Collectors.toList());
		restaurant.get().setItems(collect);
		Restaurant save = restaurantRepository.save(restaurant.get());
		return RestaurantDTO.entityToDTO(save);
	}
	
	@Override
	public RestaurantDTO getRestaurantInfo(String restaurantName) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(restaurantName);
		if (restaurant.isEmpty()) {
			throw new CustomRestaurantException("RESTAURANT NOT FOUND!!");
		}
		RestaurantDTO restaurantDTO = RestaurantDTO.entityToDTO(restaurant.get());
		return restaurantDTO;
	}
	@Override
	public RestaurantDTO deleteRestaurant(String restaurantName) throws CustomRestaurantException {
		Optional<Restaurant> restaurant = restaurantRepository.findByRestaurantNameIgnoreCase(restaurantName);
		if (restaurant.isEmpty()) {
			throw new CustomRestaurantException("RESTAURANT NOT FOUND!!");
		}
		RestaurantDTO restaurantDTO = RestaurantDTO.entityToDTO(restaurant.get());
		restaurantRepository.delete(restaurant.get());
		return restaurantDTO;
	}
	
	@Override
	public List<RestaurantDTO> searchRestaurantsWithItemname(String itemName) throws CustomRestaurantException {
		Optional<Item> item = itemRepository.findByItemNameIgnoreCase(itemName);
		if(item.isEmpty()) {
			throw new CustomRestaurantException("INVALID ITEM NAME ENTERED");
		}
		List<RestaurantDTO> dtoList=new ArrayList<>();
		List<Restaurant> restaurants = item.get().getRestaurants();
		for(Restaurant restaurant:restaurants) {
			dtoList.add( RestaurantDTO.entityToDTO(restaurant));
			}
		return dtoList;
	}


}
