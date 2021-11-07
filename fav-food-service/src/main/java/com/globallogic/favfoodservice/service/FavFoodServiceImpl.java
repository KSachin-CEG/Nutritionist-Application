package com.globallogic.favfoodservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globallogic.favfoodservice.dao.FavFoodRepository;
import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;

@Service
public class FavFoodServiceImpl implements FavFoodService{
	
	@Autowired
	private FavFoodRepository favFoodRepository;

	@Override
	public BookmarkedFood addFavFood(BookmarkedFood bookmarkedFood) {
		return favFoodRepository.save(bookmarkedFood);
	}
	
	@Override
	public String deleteFavFood(FavFoodCompositeKey id) {
		favFoodRepository.deleteById(id);
		return "Food Deleted";
	}

	@Override
	public List<Integer> getAllFavFood(int userId) {
		return favFoodRepository.getAllById(userId);
	}

}
