package com.globallogic.recommendationservice.service;

import java.util.List;

import com.globallogic.recommendationservice.model.RecommendedFoods;

public interface RecommendedFoodService {

	public void addRecommendedFood(RecommendedFoods recommendedFoods);

	public List<String> getAllRecommendedFoods(int i);

	public List<String> recommendations();

}
