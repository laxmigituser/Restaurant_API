package com.rest.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.ItemDTO;
import com.rest.dto.RestaurantDTO;
import com.rest.exception.CustomRestaurantException;
import com.rest.service.RestaurantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/addrestaurant")
	public ResponseEntity<RestaurantDTO> addRestaurant(@RequestBody @Valid RestaurantDTO dto) throws CustomRestaurantException{
		RestaurantDTO dtoo = restaurantService.addRestaurant(dto);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}
	@PutMapping("/additemtorestaurant/{restName}")
	public ResponseEntity<RestaurantDTO> addItemToRestaurant(@RequestBody List<ItemDTO> dto,@PathVariable String restName) throws CustomRestaurantException{
		RestaurantDTO dtoo = restaurantService.addItemsToRestaurant(restName,dto);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}
	@PutMapping("/removeitemtorestaurant/{restName}/{itemName}")
	public ResponseEntity<RestaurantDTO> removeItemsFromRest(@PathVariable String restName,@PathVariable String itemName) throws CustomRestaurantException{
		RestaurantDTO dtoo = restaurantService.removeItemFromRestaurant(restName,itemName);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}
	@GetMapping("/getrestaurantinfo/{restName}")
	public ResponseEntity<RestaurantDTO> getRestaurantInfo(@PathVariable String restName) throws CustomRestaurantException{
		RestaurantDTO dtoo = restaurantService.getRestaurantInfo(restName);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}
	@DeleteMapping("/deleterestaurantinfo/{restName}")
	public ResponseEntity<RestaurantDTO> deleteRestaurantInfo(@PathVariable String restName) throws CustomRestaurantException{
		RestaurantDTO dtoo = restaurantService.deleteRestaurant(restName);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}
	@GetMapping("/getrestaurantinfobyitem/{itemName}")
	public ResponseEntity<List<RestaurantDTO>> getRestaurantInfoByItem(@PathVariable String itemName) throws CustomRestaurantException{
		List<RestaurantDTO> dtoo = restaurantService.searchRestaurantsWithItemname(itemName);
		return new ResponseEntity<>(dtoo,HttpStatus.OK);
	}

}
