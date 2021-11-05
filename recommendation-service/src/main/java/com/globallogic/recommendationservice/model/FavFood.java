package com.globallogic.recommendationservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavFood {

	private int fdcId;
	private String description;
	private List<Nutrient> foodNutrients;
	private String brandOwner;
	private String brandedFoodCategory;
}
