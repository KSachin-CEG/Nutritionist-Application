package com.globallogic.favfoodservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NutrientTest {

	@InjectMocks
	private Nutrient nutrient;

	@BeforeEach
	public void setUp() {
		nutrient = new Nutrient(1003, "Protein", "G");
	}

	@AfterEach
	public void tearDown() {
		nutrient = null;
	}

	@Test
	public void testToString() {
		String expected = "Nutrient(nutrientId=1003, nutrientName=Protein, unitName=G)";
		assertEquals(expected, nutrient.toString());
	}

}