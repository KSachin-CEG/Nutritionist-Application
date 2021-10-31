package com.globallogic.favfoodservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globallogic.favfoodservice.model.BrandedFoods;

@RestController
@RequestMapping("/api/v1/favfood")
public class FavFoodController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("brand/{brandedFoodCategory}")
	public ResponseEntity<?> getFoodByBrand(@PathVariable String brandedFoodCategory) {
		BrandedFoods foods = restTemplate.getForObject(
				"https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + brandedFoodCategory,
				BrandedFoods.class);
		if(foods == null)
			return new ResponseEntity<>("No food available for this category", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(foods, HttpStatus.FOUND);
	}
}
