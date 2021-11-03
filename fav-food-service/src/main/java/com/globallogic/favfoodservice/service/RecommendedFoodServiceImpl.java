package com.globallogic.favfoodservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.favfoodservice.dao.RecommendedFoodRepository;
import com.globallogic.favfoodservice.model.RecommendedFoods;

@Service
public class RecommendedFoodServiceImpl implements RecommendedFoodService {
	
	@Autowired
	private RecommendedFoodRepository recommendedFoodRepository;

	@Override
	public void addRecommendedFood(RecommendedFoods recommendedFoods) {
		recommendedFoodRepository.save(recommendedFoods);
	}

	@Override
	public List<String> getAllRecommendedFoods(int i) {
		return recommendedFoodRepository.getAllRecommendedFoods(i);
	}

}
