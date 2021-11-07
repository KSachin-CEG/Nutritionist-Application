package com.globallogic.favfoodservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.favfoodservice.dao.FavFoodRepository;
import com.globallogic.favfoodservice.model.BookmarkedFood;
import com.globallogic.favfoodservice.model.FavFoodCompositeKey;

@ExtendWith(MockitoExtension.class)
class FavFoodServiceImplTest {

	@Mock
	private FavFoodRepository favFoodRepository;

	@InjectMocks
	private FavFoodServiceImpl favFoodService;

	private BookmarkedFood bookmarkedFood = new BookmarkedFood(7, 373052);
	FavFoodCompositeKey favFoodCompositeKey = new FavFoodCompositeKey(7, 373052);
	
	@Test
	public void givenValidFoodToSaveThenShouldReturnBookmarkedFood() {
		when(favFoodRepository.save(bookmarkedFood)).thenReturn(bookmarkedFood);
		BookmarkedFood addedFavFood = favFoodService.addFavFood(bookmarkedFood);
		assertEquals(bookmarkedFood, addedFavFood);
		verify(favFoodRepository, times(1)).save(bookmarkedFood);
	}
	
	@Test
	public void givenValidFoodToDeleteThenShouldDeleteFood() {
		assertEquals("Food Deleted", favFoodService.deleteFavFood(favFoodCompositeKey));
	}
	
	@Test
	public void givenGetAllFoodThenShouldReturnListOfFavFoodOfSpecificUser() {
		List<Integer> foodIds = Arrays.asList(373052, 534358); 
		when(favFoodRepository.getAllById(7)).thenReturn(foodIds);
		assertEquals(foodIds, favFoodService.getAllFavFood(7));
	}

}
