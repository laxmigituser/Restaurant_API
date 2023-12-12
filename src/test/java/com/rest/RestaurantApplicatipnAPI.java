package com.rest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.entity.Item;
import com.rest.entity.Restaurant;
import com.rest.repository.ItemRepository;
import com.rest.repository.RestaurantRepository;

@SpringBootTest
@SpringBootConfiguration
public class RestaurantApplicatipnAPI {
//@Autowired
//private RestaurantRepository restaurantRepo;
//@Autowired
//private ItemRepository itemRepo;

	@Test
	@Disabled
	void contextLoads() {
		String regex=".*[\\S]+.*";
		String s="wert wert 234";
		//System.out.println(s.matches(regex));
	}
	@Test
	void check() {
		List<String> itemName=new ArrayList<>();
		itemName.add("chilli");
		itemName.add("chilly");
		List<String> items = new ArrayList<>();
		items.add("chilli");
		items.add("chilly");
		boolean b=itemName.stream().anyMatch(s->!(items.contains(s)));
		System.out.println(b);
	}

}
