package com.globallogic.favfoodservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookmarkedFoodTest {

	@InjectMocks
	private BookmarkedFood bookmarkedFood;

	@BeforeEach
	public void setUp() {
		bookmarkedFood = new BookmarkedFood(7, 373052);
	}

	@AfterEach
	public void tearDown() {
		bookmarkedFood = null;
	}

	@Test
	public void testToString() {
		String expected = "BookmarkedFood(userId=7, fdcId=373052)";
		assertEquals(expected, bookmarkedFood.toString());
	}

}
