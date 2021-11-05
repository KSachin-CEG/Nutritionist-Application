package com.globallogic.recommendationservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globallogic.recommendationservice.model.BrandedFoods;
import com.globallogic.recommendationservice.model.FavFood;
import com.globallogic.recommendationservice.model.RecommendedFoods;
import com.globallogic.recommendationservice.service.RecommendedFoodService;

@RestController
@RequestMapping("/recommendFood")
public class RecommendedFoodController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RecommendedFoodService recommendedFoodService;

	@GetMapping("/{brandedFoodCategory}")
	public ResponseEntity<String> addRecommendedFood(@PathVariable String brandedFoodCategory) {

		/* write logic to check get userid if authorized */
		RecommendedFoods recommendedFoods = new RecommendedFoods(7, brandedFoodCategory);
		recommendedFoodService.addRecommendedFood(recommendedFoods);
		return new ResponseEntity<>("Added to recommendations", HttpStatus.OK);
	}

	@GetMapping("/getAllRecommendations")
	public ResponseEntity<List<FavFood>> getRecommendedFoods() {

		/* write logic to check get userid if authorized */

		List<FavFood> recommendedFoods = new ArrayList<>();
		List<String> recommendedFoodBrandList = recommendedFoodService.getAllRecommendedFoods(7);
		List<String> recommendedFoodBrands = recommendations();
		
		for (String brand : recommendedFoodBrandList) {
			BrandedFoods foods = searchByBrand(brand);
			recommendedFoods.addAll(foods.getFoods());
		}

		for (String brand : recommendedFoodBrands) {
			BrandedFoods foods = searchByBrand(brand);
			recommendedFoods.addAll(foods.getFoods());
		}

		return new ResponseEntity<>(recommendedFoods, HttpStatus.OK);
	}

	// Get recommendations even when user does not login
	public List<String> recommendations() {
		return recommendedFoodService.recommendations();
	}

	// Search food by brand
	public BrandedFoods searchByBrand(String brandedFoodCategory) {
		return restTemplate.getForObject(
				"https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + brandedFoodCategory,
				BrandedFoods.class);
	}

}
