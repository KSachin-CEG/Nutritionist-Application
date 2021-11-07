package com.globallogic.recommendationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.recommendationservice.dao.RecommendedFoodRepository;
import com.globallogic.recommendationservice.model.RecommendedFoods;

@ExtendWith(MockitoExtension.class)
class RecommendedFoodServiceTest {

	@Mock
	private RecommendedFoodRepository recommendedFoodRepository;

	@InjectMocks
	private RecommendedFoodServiceImpl recommendedFoodServiceImpl;

	private RecommendedFoods recommendedFoods;
	private List<String> recommendedFoodBrandList = new ArrayList<>();
	private List<String> defaultRecommendedBrandList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		recommendedFoods = new RecommendedFoods(7, "Cheddar Cheese");
		recommendedFoodBrandList.add("Cheddar Cheese");
		defaultRecommendedBrandList.add("Honey");
		defaultRecommendedBrandList.add("Cheddar Cheese");
		defaultRecommendedBrandList.add("Hey Song, Sarsaparilla");
		defaultRecommendedBrandList.add("Baby Ruth");
		defaultRecommendedBrandList.add("Sesame Oil");
		defaultRecommendedBrandList.add("Fruit leather and fruit snacks candy");
	}

	@AfterEach
	public void tearDown() {
		recommendedFoods = null;
		recommendedFoodBrandList = null;
		defaultRecommendedBrandList = null;
	}

	@Test
	public void givenFoodToAddThenShouldReturnSavedFood() {
		when(recommendedFoodRepository.save(recommendedFoods)).thenReturn(recommendedFoods);
		RecommendedFoods addedRecommendedFood = recommendedFoodServiceImpl.addRecommendedFood(recommendedFoods);
		assertEquals(recommendedFoods, addedRecommendedFood);
		verify(recommendedFoodRepository, times(1)).save(any());
	}

	@Test
	public void givenGetAllRecommendedFoodsThenShouldReturnListOfAllRecommendedFoods() {
		when(recommendedFoodRepository.getAllRecommendedFoods(7)).thenReturn(recommendedFoodBrandList);
		List<String> brandList = recommendedFoodServiceImpl.getAllRecommendedFoods(7);
		assertEquals(recommendedFoodBrandList, brandList);
	}

	@Test
	public void testRecomendationsThenShouldReturnDefaultRecommendationsList() {
		when(recommendedFoodRepository.recommendations()).thenReturn(defaultRecommendedBrandList);
		List<String> brandList = recommendedFoodServiceImpl.recommendations();
		assertEquals(defaultRecommendedBrandList.subList(0, 5), brandList);
	}

}
