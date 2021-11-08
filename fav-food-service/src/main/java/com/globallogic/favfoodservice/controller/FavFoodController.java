package com.globallogic.favfoodservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.BrandedFoods;
import com.globallogic.favfoodservice.model.FavFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;
import com.globallogic.favfoodservice.service.FavFoodService;

@RestController
@RequestMapping("/favfood")
public class FavFoodController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	@Qualifier("internalApiBean")
	private RestTemplate internalApiBean;

	@Autowired
	@Qualifier("externalApiBean")
	private RestTemplate externalApiBean;

	@Autowired
	private FavFoodService favFoodService;

	public FavFood getFoodById(int fdcId) {
		try {
			FavFood favFood = externalApiBean.getForObject(
					"https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=" + apiKey, FavFood.class);
			return favFood;
		} catch (Exception e) {
			return null;
		}
	}

	public BrandedFoods searchFoodByBrand(String brandedFoodCategory) {
		return externalApiBean.getForObject(
				"https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=" + brandedFoodCategory,
				BrandedFoods.class);
	}

	@GetMapping("brand/{brandedFoodCategory}")
	public ResponseEntity<?> getFoodByBrand(@PathVariable String brandedFoodCategory) {

		/* write logic to check whether a user is logged in or not */

		BrandedFoods foods = searchFoodByBrand(brandedFoodCategory);
		if (foods.getFoods().isEmpty())
			return new ResponseEntity<>("No food available for this category", HttpStatus.NOT_FOUND);

		/* if(user != null) - logic to verify user is logged in */
		internalApiBean.getForObject("http://recommendation-service/recommendFood/" + brandedFoodCategory,
				String.class);

		return new ResponseEntity<>(foods, HttpStatus.FOUND);
	}

	@GetMapping("/{fdcId}")
	public ResponseEntity<?> addFavFood(@PathVariable int fdcId) {

		/* write logic to check get userid if authorized */

		FavFood food = getFoodById(fdcId);
		if (food == null)
			return new ResponseEntity<>("Food with ID : " + fdcId + " does not exist", HttpStatus.NOT_FOUND);
		BookmarkedFood bookmarkedFood = new BookmarkedFood(7, food.getFdcId());

		internalApiBean.getForObject("http://recommendation-service/recommendFood/" + food.getBrandedFoodCategory(),
				String.class);

		return new ResponseEntity<>(favFoodService.addFavFood(bookmarkedFood), HttpStatus.FOUND);
	}

	@DeleteMapping("/{fdcId}")
	public ResponseEntity<?> deleteFavFood(@PathVariable int fdcId) {

		/* write logic to check get userid if authorized */

		FavFood food = getFoodById(fdcId);
		if (food == null)
			return new ResponseEntity<>("Food with ID : " + fdcId + " does not exist", HttpStatus.NOT_FOUND);
		FavFoodCompositeKey id = new FavFoodCompositeKey(7, fdcId);
		String response = favFoodService.deleteFavFood(id);
		return new ResponseEntity<>("Food with ID : " + food.getFdcId() + " has been deleted", HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllFavFood() {

		/* write logic to check get userid if authorized */

		List<Integer> favFoodList = favFoodService.getAllFavFood(7);
		if (favFoodList.isEmpty())
			return new ResponseEntity<>("No food is bookmarked till now", HttpStatus.NOT_FOUND);
		String commaSeparatedFoodIds = String.join(",",
				favFoodList.stream().map(id -> id.toString()).collect(Collectors.toList()));

		@SuppressWarnings("unchecked")
		ArrayList<FavFood> foods = (ArrayList<FavFood>) externalApiBean.getForObject(
				"https://api.nal.usda.gov/fdc/v1/foods?fdcIds=" + commaSeparatedFoodIds + "&api_key=" + apiKey,
				ArrayList.class);
		return new ResponseEntity<>(foods, HttpStatus.OK);
	}
}
