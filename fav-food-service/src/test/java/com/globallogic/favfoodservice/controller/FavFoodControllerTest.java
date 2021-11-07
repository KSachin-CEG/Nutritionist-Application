package com.globallogic.favfoodservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.BrandedFoods;
import com.globallogic.favfoodservice.model.FavFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;
import com.globallogic.favfoodservice.model.Nutrient;
import com.globallogic.favfoodservice.service.FavFoodService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class FavFoodControllerTest {

	@InjectMocks
	private FavFoodController favFoodController;

	@MockBean
	private FavFoodService favFoodService;

	@Mock
	private RestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	private Nutrient nutrient1;
	private Nutrient nutrient2;
	private FavFood favFood;

	private List<FavFood> favFoodList = new ArrayList<>();
	private List<Nutrient> nutrientsList = new ArrayList<>();

	// private String apiKey = "g4SxbRXf18hw3W56XFtgjPI8dC9roUkUiwjBU0vA";

	@BeforeEach
	public void setUp() {
		nutrient1 = new Nutrient(1003, "Protein", "G");
		nutrient2 = new Nutrient(1004, "Total lipid (fat)", "G");
		nutrientsList.add(nutrient1);
		nutrientsList.add(nutrient2);
		favFood = new FavFood(2057648, "Cheddar Cheese", nutrientsList, "Grafton Village Cheese Co, LLC", "Amul");
		favFoodList.add(favFood);
	}

	@AfterEach
	public void tearDown() {
		nutrient1 = nutrient2 = null;
		nutrientsList = null;
		favFood = null;
		favFoodList = null;
	}

	@Test
	public void givenInvalidFoodBrandThenShouldReturnNotFoundHttpCode() throws Exception {
		String apiKey = null;
		when(restTemplate.getForObject(
				"https://api.nal.usda.gov/fdc/v1/foods/search?api_key=" + apiKey + "&query=InvalidBrand",
				BrandedFoods.class)).thenReturn(new BrandedFoods(new ArrayList<FavFood>()));

		ResponseEntity<?> result = favFoodController.getFoodByBrand("InvalidBrand");
		mockMvc.perform(get("/favfood/brand/InvalidBrand")).andExpect(status().isNotFound());
		assertEquals("No food available for this category", result.getBody());
	}

	@Test
	public void givenValidFoodBrandThenShouldReturnHttpOkCode() throws Exception {
		mockMvc.perform(get("/favfood/brand/Cheddar Cheese")).andExpect(status().isFound());
	}

	@Test
	public void givenInvalidFoodIdToAddFoodThenShouldReturnNotFoundHttpStatusCode() throws Exception {
		mockMvc.perform(get("/favfood/123")).andExpect(status().isNotFound());
	}

	@Test
	public void givenValidFoodIdToAddFoodThenShouldReturnAddedFood() throws Exception {
		BookmarkedFood bookmarkedFood = new BookmarkedFood(7, 373052);
		when(favFoodService.addFavFood(any())).thenReturn(bookmarkedFood);
		BookmarkedFood food = favFoodService.addFavFood(bookmarkedFood);
		mockMvc.perform(get("/favfood/373052")).andExpect(status().isFound());
		assertEquals(bookmarkedFood, food);
	}

	@Test
	void givenInvalidFoodIdToDeleteFoodThenShouldReturnNotFoundHttpStatusCode() throws Exception {
		mockMvc.perform(delete("/favfood/123")).andExpect(status().isNotFound());
	}

	@Test
	void givenValidFoodIdToDeleteFoodThenShouldReturnOkHttpStatusCode() throws Exception {
		FavFoodCompositeKey favFoodCompositeKey = new FavFoodCompositeKey(7, 373052);
		when(favFoodService.deleteFavFood(favFoodCompositeKey)).thenReturn("Food Deleted");
		mockMvc.perform(delete("/favfood/373052")).andExpect(status().isOk());
	}

	@Test
	void testGetAllFavFoodWhenUserNotBookmarkedAnyFoodThenShouldReturnNotFoundHttpStatusCode() throws Exception {
		when(favFoodService.getAllFavFood(7)).thenReturn(new ArrayList<Integer>());
		ResponseEntity<?> result = favFoodController.getAllFavFood();
		mockMvc.perform(get("/favfood")).andExpect(status().isNotFound());
		assertEquals("No food is bookmarked till now", result.getBody());
	}

	@Test
	void testGetAllFavFoodThenShouldReturnOkHttpStatusCode() throws Exception {
		when(favFoodService.getAllFavFood(7)).thenReturn(Arrays.asList(2057648));
		ResponseEntity<?> result = favFoodController.getAllFavFood();
		mockMvc.perform(get("/favfood/getAll")).andExpect(status().isOk());
	}

}
