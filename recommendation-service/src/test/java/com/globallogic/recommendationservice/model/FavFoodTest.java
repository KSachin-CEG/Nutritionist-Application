package com.globallogic.recommendationservice.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FavFoodTest {

	@InjectMocks
	private FavFood favFood;

	private Nutrient nutrient1;
	private Nutrient nutrient2;

	private List<Nutrient> nutrientsList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		nutrient1 = new Nutrient(1003, "Protein", "G");
		nutrient2 = new Nutrient(1004, "Total lipid (fat)", "G");
		nutrientsList.add(nutrient1);
		nutrientsList.add(nutrient2);
		favFood = new FavFood(2057648, "Cheddar Cheese", nutrientsList, "Grafton Village Cheese Co, LLC", "Amul");
	}

	@AfterEach
	public void tearDown() {
		nutrient1 = nutrient2 = null;
		nutrientsList = null;
		favFood = null;
	}

	@Test
	public void testToString() {
		String expected = "FavFood(fdcId=2057648, description=Cheddar Cheese, foodNutrients=[Nutrient(nutrientId=1003, nutrientName=Protein, unitName=G), Nutrient(nutrientId=1004, nutrientName=Total lipid (fat), unitName=G)], brandOwner=Grafton Village Cheese Co, LLC, brandedFoodCategory=Amul)";
		assertEquals(expected, favFood.toString());
	}

}
