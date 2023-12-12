package com.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	Optional<Item> findByItemNameIgnoreCase(String itemName);
	List<Item> findByPrice(int price);
}
