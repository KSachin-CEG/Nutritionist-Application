package com.globallogic.favfoodservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {

	private int nutrientId;
	private String nutrientName;
	private String unitName;

}
