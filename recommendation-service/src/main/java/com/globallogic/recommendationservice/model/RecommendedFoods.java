package com.globallogic.recommendationservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(RecommendedFoodCompositeKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedFoods {

	@Id
	private int userId;

	@Id
	private String description;
}
