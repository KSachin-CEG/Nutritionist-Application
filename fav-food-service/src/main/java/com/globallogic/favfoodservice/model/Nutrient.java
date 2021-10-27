package com.globallogic.favfoodservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {

	private int id;
	private String name;
	private String unitName;

}
