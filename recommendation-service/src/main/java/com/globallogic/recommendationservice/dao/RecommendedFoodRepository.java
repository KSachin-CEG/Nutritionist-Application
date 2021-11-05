package com.globallogic.recommendationservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globallogic.recommendationservice.model.RecommendedFoods;

@Repository
public interface RecommendedFoodRepository extends JpaRepository<RecommendedFoods, RecommendedFoodRepository> {

	@Query("select description from RecommendedFoods r where userId=?1")
	public List<String> getAllRecommendedFoods(int userId);

	@Query("select description from RecommendedFoods r group by description order by count(description) desc")
	public List<String> recommendations();

}
