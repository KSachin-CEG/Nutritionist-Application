package com.globallogic.favfoodservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;

@Repository
public interface FavFoodRepository extends JpaRepository<BookmarkedFood, FavFoodCompositeKey>{
	
	@Query("select fdcId from BookmarkedFood b where userId=?1")
	public List<Integer> getAllById(int userId);

}
