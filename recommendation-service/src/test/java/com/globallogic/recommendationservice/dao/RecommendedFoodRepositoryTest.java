package com.globallogic.recommendationservice.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.globallogic.recommendationservice.model.RecommendedFoods;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RecommendedFoodRepositoryTest {

	@Autowired
	private RecommendedFoodRepository recommendedFoodRepository;
	
	private RecommendedFoods recommendedFoods;
	private List<String> recommendedFoodBrandList = new ArrayList<>();
	private List<String> defaultRecommendedBrandList = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		recommendedFoods = new RecommendedFoods(7, "Cheddar Cheese");
		recommendedFoodBrandList.add("Cheddar Cheese");
		defaultRecommendedBrandList.add("Cheddar Cheese");
		defaultRecommendedBrandList.add("Honey");
	}

	@AfterEach
	public void tearDown() {
		recommendedFoods = null;
		recommendedFoodBrandList = null;
		defaultRecommendedBrandList = null;
	}
	
	@Test
	void testGetAllRecommendedFoodsThenShouldReturnRecommendedBrandsForSpecificUser() {
		recommendedFoodRepository.save(recommendedFoods);
		assertEquals(recommendedFoodBrandList, recommendedFoodRepository.getAllRecommendedFoods(7));
	}

	@Test
	void testRecommendations() {
		recommendedFoodRepository.save(new RecommendedFoods(6, "Honey"));
		recommendedFoodRepository.save(recommendedFoods);
		assertEquals(defaultRecommendedBrandList, recommendedFoodRepository.recommendations());
	}

}
