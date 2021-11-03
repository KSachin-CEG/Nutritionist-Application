package com.globallogic.favfoodservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavFoodCompositeKey implements Serializable {

	private int userId;
	private int fdcId;
}
