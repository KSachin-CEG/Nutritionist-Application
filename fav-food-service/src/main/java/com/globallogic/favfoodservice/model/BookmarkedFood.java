package com.globallogic.favfoodservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@IdClass(FavFoodCompositeKey.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkedFood {

	@Id
	private int userId;

	@Id
	private int fdcId;

}
