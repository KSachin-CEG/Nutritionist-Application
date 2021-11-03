package com.globallogic.favfoodservice.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]+", message = "Username should include only alphabets, numbers and underscore")
	private String userName;
	
	@Pattern(regexp = "(?=[A-Za-z0-9@#$%^&+!=]+$)^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+!=])(?=.{8,}).*$", message = "Password should contain atleast 1 special character, 1 upper alphabet, 1 lower alphabet, 1 digit and length should be greater than 8")
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	
	@Pattern(regexp = "(?i)(male)|(female)|(other)", message = "Choose either Male, Female or Other")
	private String gender;
	
	private String country;
	private String region;
	
	@Pattern(regexp = "(?i)(veg)|(nonveg)", message = "Choose either Veg or Nonveg")
	private String foodType;

}
