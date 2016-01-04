package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.User;

public class UserRowMapper implements RowMapper<User>{
	public User mapRow(ResultSet resultSet,int row) throws SQLException
	{
		String userId;
		String password;
		String authType;
		String firstName;
		String lastName;
		String preferName;
	    String gender;
	    String birthday;
	    String address;
	    String city;
	    String state;
	    String preferPets;
	    String feedingPets;
	    String petName;
	    String career;
	    String createdDate;
	    String deletedDate;
		
		User user;
		
		//Get value from result set of query
		
		userId = resultSet.getString("user_id") == null?"":resultSet.getString("user_id");
		password = resultSet.getString("password") == null?"":resultSet.getString("password");
		authType = resultSet.getString("auth_type") == null?"":resultSet.getString("auth_type");
		gender = resultSet.getString("gender") == null?"":resultSet.getString("gender");
		birthday = resultSet.getDate("DOB") ==  null?"":Formatter.date2String(resultSet.getDate("DOB"));
		firstName = resultSet.getString("first_name") == null?"":resultSet.getString("first_name");
		lastName = resultSet.getString("last_name") == null?"":resultSet.getString("last_name");
		preferName = resultSet.getString("prefer_name") == null?"":resultSet.getString("prefer_name");
		address = resultSet.getString("address") == null?"":resultSet.getString("address");
		city = resultSet.getString("city") == null?"":resultSet.getString("city");
		state = resultSet.getString("state") == null?"":resultSet.getString("state");
		preferPets = resultSet.getString("prefer_pets") == null?"":resultSet.getString("prefer_pets");
		feedingPets = resultSet.getString("feeding_pets") == null?"":resultSet.getString("feeding_pets");
		petName = resultSet.getString("pet_name") == null?"":resultSet.getString("pet_name");
		career = resultSet.getString("career") == null?"":resultSet.getString("career");
		createdDate = resultSet.getString("created_date") == null?"":Formatter.datetime2String(resultSet.getTime("created_date"));
		deletedDate = resultSet.getString("deleted_date") == null?"":Formatter.datetime2String(resultSet.getTime("career"));
		
		//Set value to object
		user = new User();
		
		user.setUserId(userId);
		user.setPassword(password);
		user.setAuthType(authType);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setFirstName(preferName);
		user.setAddress(address);
		user.setCity(city);
		user.setState(state);
		user.setPreferPets(preferPets);
		user.setFeedingPets(feedingPets);
		user.setPetName(petName);
		user.setCareer(career);
		user.setCreatedDate(createdDate);
		user.setDeletedDate(deletedDate);
		
		return user;
	}
}
