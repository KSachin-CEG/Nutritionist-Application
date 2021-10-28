package com.globallogic.favfoodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globallogic.favfoodservice.model.FavFood;
import com.globallogic.favfoodservice.service.FavFoodService;

@RestController
@RequestMapping("/api/v1/favfood")
public class FavFoodController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FavFoodService favFoodService;

	@GetMapping("/{favfoodId}")
	public FavFood getFoodById(@PathVariable int favfoodId) {
		FavFood favFood = restTemplate.getForObject("https://api.nal.usda.gov/fdc/v1/food/" + favfoodId + "?api_key=" + apiKey,
				FavFood.class);
		return favFood;
	}
	
	@GetMapping("brand/{brandedFoodCategory}")
	public FavFood getFoodByBrand(@PathVariable String brandedFoodCategory) {
		FavFood foods = restTemplate.getForObject("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + brandedFoodCategory,
				FavFood.class);
		return foods;
	}
}
