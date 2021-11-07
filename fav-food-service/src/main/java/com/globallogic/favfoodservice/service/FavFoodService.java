package com.globallogic.favfoodservice.service;

import java.util.List;

import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;

public interface FavFoodService {

	public BookmarkedFood addFavFood(BookmarkedFood bookmarkedFood);

	public String deleteFavFood(FavFoodCompositeKey id);

	public List<Integer> getAllFavFood(int userId);

}
