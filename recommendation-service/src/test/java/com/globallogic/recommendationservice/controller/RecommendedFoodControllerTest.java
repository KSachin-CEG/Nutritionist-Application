package com.globallogic.recommendationservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.globallogic.recommendationservice.model.RecommendedFoods;
import com.globallogic.recommendationservice.service.RecommendedFoodService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class RecommendedFoodControllerTest {

	@MockBean
	private RecommendedFoodService recommendedFoodService;

	@InjectMocks
	private RecommendedFoodController recommendedFoodController;

	@Autowired
	private MockMvc mockMvc;

	private RecommendedFoods recommendedFoods;
	private List<String> recommendedFoodBrandList = new ArrayList<>();
	private List<String> defaultRecommendedBrandList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		recommendedFoods = new RecommendedFoods(7, "Cheddar Cheese");
		recommendedFoodBrandList.add("Cheddar Cheese");
		defaultRecommendedBrandList.add("Honey");
		defaultRecommendedBrandList.add("Cheddar Cheese");
	}

	@AfterEach
	public void tearDown() {
		recommendedFoods = null;
		recommendedFoodBrandList = null;
		defaultRecommendedBrandList = null;
	}

	@Test
	public void testWhenFoodAddedToRecommendationsThenShouldReturnOkHttpStatusCode() throws Exception {
		when(recommendedFoodService.addRecommendedFood(any())).thenReturn(recommendedFoods);
		ResponseEntity<String> result = recommendedFoodController.addRecommendedFood("Cheddar Cheese");
		mockMvc.perform(get("/recommendFood/Cheddar Cheese")).andExpect(status().isOk());
		assertEquals("Added to recommendations", result.getBody());
	}
	
	@Test
	public void testGetAllRecommendationsThenShouldReturnOkHttpStatusCode() throws Exception {
		when(recommendedFoodService.getAllRecommendedFoods(7)).thenReturn(recommendedFoodBrandList);
		when(recommendedFoodService.recommendations()).thenReturn(defaultRecommendedBrandList);
		mockMvc.perform(get("/recommendFood/getAllRecommendations")).andExpect(status().isOk());
	}
}
