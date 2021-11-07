package com.globallogic.recommendationservice.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BrandedFoodsTest {

	@InjectMocks
	private BrandedFoods brandedFoods;

	private FavFood favFood1;
	private FavFood favFood2;
	private Nutrient nutrient1;
	private Nutrient nutrient2;

	private List<FavFood> favFoodList = new ArrayList<>();
	private List<Nutrient> nutrientsList = new ArrayList<>();

	@BeforeEach
	public void setUp() {
		nutrient1 = new Nutrient(1003, "Protein", "G");
		nutrient2 = new Nutrient(1004, "Total lipid (fat)", "G");
		nutrientsList.add(nutrient1);
		nutrientsList.add(nutrient2);
		favFood1 = new FavFood(2057648, "Cheddar Cheese", nutrientsList, "Grafton Village Cheese Co, LLC", "Amul");
		favFood2 = new FavFood(2104568, "Honey", nutrientsList, "Unified Grocers, Inc.", "Amul");
		favFoodList.add(favFood1);
		favFoodList.add(favFood2);
		brandedFoods = new BrandedFoods(favFoodList);
	}

	@AfterEach
	public void tearDown() {
		nutrient1 = nutrient2 = null;
		nutrientsList = null;
		favFood1 = favFood2 = null;
		favFoodList = null;
		brandedFoods = null;
	}

	@Test
	public void testToString() {
		String expected = "BrandedFoods(foods=[FavFood(fdcId=2057648, description=Cheddar Cheese, foodNutrients=[Nutrient(nutrientId=1003, nutrientName=Protein, unitName=G), Nutrient(nutrientId=1004, nutrientName=Total lipid (fat), unitName=G)], brandOwner=Grafton Village Cheese Co, LLC, brandedFoodCategory=Amul), FavFood(fdcId=2104568, description=Honey, foodNutrients=[Nutrient(nutrientId=1003, nutrientName=Protein, unitName=G), Nutrient(nutrientId=1004, nutrientName=Total lipid (fat), unitName=G)], brandOwner=Unified Grocers, Inc., brandedFoodCategory=Amul)])";
		assertEquals(expected, brandedFoods.toString());
	}

}
