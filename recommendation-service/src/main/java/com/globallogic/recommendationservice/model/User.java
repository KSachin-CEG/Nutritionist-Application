package com.globallogic.recommendationservice.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int userId;
	private String userName;
	private String password;
	private Date dob;
	private String gender;
	private String country;
	private String region;
	private String foodType;

}
