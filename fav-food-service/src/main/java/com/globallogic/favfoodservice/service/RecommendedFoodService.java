package com.globallogic.favfoodservice.service;

import java.util.List;

import com.globallogic.favfoodservice.model.RecommendedFoods;

public interface RecommendedFoodService {

	public void addRecommendedFood(RecommendedFoods recommendedFoods);

	public List<String> getAllRecommendedFoods(int i);

}
