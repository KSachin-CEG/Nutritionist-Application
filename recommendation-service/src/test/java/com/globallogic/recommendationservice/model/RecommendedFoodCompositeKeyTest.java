package com.globallogic.recommendationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecommendedFoodCompositeKeyTest {

	@InjectMocks
	private RecommendedFoodCompositeKey recommendedFoodCompositeKey;

	@BeforeEach
	public void setUp() {
		recommendedFoodCompositeKey = new RecommendedFoodCompositeKey(7, "Cheddar Cheese");
	}

	@AfterEach
	public void tearDown() {
		recommendedFoodCompositeKey = null;
	}

	@Test
	public void testToString() {
		String expected = "RecommendedFoodCompositeKey(userId=7, description=Cheddar Cheese)";
		assertEquals(expected, recommendedFoodCompositeKey.toString());
	}

}
