package com.globallogic.recommendationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.recommendationservice.dao.RecommendedFoodRepository;
import com.globallogic.recommendationservice.model.RecommendedFoods;

@Service
public class RecommendedFoodServiceImpl implements RecommendedFoodService {

	@Autowired
	private RecommendedFoodRepository recommendedFoodRepository;

	@Override
	public RecommendedFoods addRecommendedFood(RecommendedFoods recommendedFoods) {
		return recommendedFoodRepository.save(recommendedFoods);
	}

	@Override
	public List<String> getAllRecommendedFoods(int i) {
		return recommendedFoodRepository.getAllRecommendedFoods(i);
	}

	@Override
	public List<String> recommendations() {
		List<String> brandList = recommendedFoodRepository.recommendations();
		if(brandList.size()>5)
			brandList = brandList.subList(0, 5);
		return brandList;
	}

}
