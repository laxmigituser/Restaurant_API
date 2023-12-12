package com.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.dto.ItemDTO;
import com.rest.exception.CustomRestaurantException;
import com.rest.service.ItemService;

import jakarta.validation.Valid;

//import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@PostMapping("/addItem")
	public ResponseEntity<ItemDTO> addNewItem(@Valid@RequestBody ItemDTO dto) throws CustomRestaurantException {
		ItemDTO addedItemDTO = itemService.addItem(dto);
		return new ResponseEntity(addedItemDTO, HttpStatus.OK);
	}
	@PutMapping("/updateItemname/{oldName}/{newName}")
	public ResponseEntity<ItemDTO> updateItemName(@PathVariable String oldName,@PathVariable String newName) throws CustomRestaurantException{
		ItemDTO dto = itemService.updateItemname(oldName, newName);
		return new ResponseEntity<ItemDTO>(dto, HttpStatus.OK);
	}
	@PutMapping("/updateItemprice/{itemName}/{price}")
	public ResponseEntity<ItemDTO> updateItemPrice(@PathVariable String itemName,@PathVariable int price) throws CustomRestaurantException{
		ItemDTO dto = itemService.updateItemPrice(itemName, price);
		return new ResponseEntity<ItemDTO>(dto, HttpStatus.OK);
	}
	@GetMapping("/getItems/{restaurantName}")
	public ResponseEntity<List<ItemDTO>> searchItemsWithRestaurantName(@PathVariable String restaurantName) throws CustomRestaurantException{
		List<ItemDTO> dto = itemService.searchItemsWithRestaurantname(restaurantName.trim());	
		return new ResponseEntity<List<ItemDTO>>(dto, HttpStatus.OK);
	}
	@DeleteMapping("/deleteitem/{itemName}")
	public ResponseEntity deleteItemWithname(@PathVariable String itemName) throws CustomRestaurantException{
		itemService.deleteItem(itemName);
		return new ResponseEntity<>( HttpStatus.OK);
	}
}
