package com.globallogic.favfoodservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FavFoodCompositeKeyTest {

	@InjectMocks
	private FavFoodCompositeKey favFoodCompositeKey;

	@BeforeEach
	public void setUp() {
		favFoodCompositeKey = new FavFoodCompositeKey(7, 373052);
	}

	@AfterEach
	public void tearDown() {
		favFoodCompositeKey = null;
	}

	@Test
	public void testToString() {
		String expected = "FavFoodCompositeKey(userId=7, fdcId=373052)";
		assertEquals(expected, favFoodCompositeKey.toString());
	}

}
