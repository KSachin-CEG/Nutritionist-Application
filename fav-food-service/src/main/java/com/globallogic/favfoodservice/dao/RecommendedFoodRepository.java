package com.globallogic.favfoodservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globallogic.favfoodservice.model.FavFoodCompositeKey;
import com.globallogic.favfoodservice.model.RecommendedFoods;

@Repository
public interface RecommendedFoodRepository extends JpaRepository<RecommendedFoods, RecommendedFoodRepository> {

	@Query("select description from RecommendedFoods b where userId=?1")
	public List<String> getAllRecommendedFoods(int userId);

}
