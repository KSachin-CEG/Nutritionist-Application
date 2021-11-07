package com.globallogic.recommendationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecommendedFoodsTest {

	@InjectMocks
	private RecommendedFoods recommendedFoods;

	@BeforeEach
	public void setUp() {
		recommendedFoods = new RecommendedFoods(7, "Cheddar Cheese");
	}

	@AfterEach
	public void tearDown() {
		recommendedFoods = null;
	}

	@Test
	public void testToString() {
		String expected = "RecommendedFoods(userId=7, description=Cheddar Cheese)";
		assertEquals(expected, recommendedFoods.toString());
	}

}
