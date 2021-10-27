package com.globallogic.favfoodservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandedFoods {

	private List<FavFood> foods;
	
}
