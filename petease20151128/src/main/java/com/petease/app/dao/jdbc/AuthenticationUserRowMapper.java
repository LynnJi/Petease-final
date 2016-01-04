package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.User;

public class AuthenticationUserRowMapper implements RowMapper<Authentication>{
	public Authentication mapRow(ResultSet resultSet,int row) throws SQLException
	{
		String token;
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
	    String userCreatedDate;
	    String userDeletedDate;
	    String authCreatedDate;
	    String authDeletedDate;
		
		User user;
		Authentication auth;
		
		//Get value from result set of query
		
		userId = resultSet.getString("u_user_id") == null?"":resultSet.getString("u_user_id");
		password = resultSet.getString("u_password") == null?"":resultSet.getString("u_password");
		authType = resultSet.getString("u_auth_type") == null?"":resultSet.getString("u_auth_type");
		gender = resultSet.getString("u_gender") == null?"":resultSet.getString("u_gender");
		birthday = resultSet.getDate("u_DOB") ==  null?"":Formatter.date2String(resultSet.getDate("u_DOB"));
		firstName = resultSet.getString("u_first_name") == null?"":resultSet.getString("u_first_name");
		lastName = resultSet.getString("u_last_name") == null?"":resultSet.getString("u_last_name");
		preferName = resultSet.getString("u_prefer_name") == null?"":resultSet.getString("u_prefer_name");
		address = resultSet.getString("u_address") == null?"":resultSet.getString("u_address");
		city = resultSet.getString("u_city") == null?"":resultSet.getString("u_city");
		state = resultSet.getString("u_state") == null?"":resultSet.getString("u_state");
		preferPets = resultSet.getString("u_prefer_pets") == null?"":resultSet.getString("u_prefer_pets");
		feedingPets = resultSet.getString("u_feeding_pets") == null?"":resultSet.getString("u_feeding_pets");
		petName = resultSet.getString("u_pet_name") == null?"":resultSet.getString("u_pet_name");
		career = resultSet.getString("u_career") == null?"":resultSet.getString("u_career");
		userCreatedDate = resultSet.getString("u_created_date") == null?"":Formatter.datetime2String(resultSet.getDate("u_created_date"));
		userDeletedDate = resultSet.getString("u_deleted_date") == null?"":Formatter.datetime2String(resultSet.getDate("u_career"));
		token = resultSet.getString("a_token") == null?"":resultSet.getString("a_token");
		authCreatedDate = resultSet.getString("a_created_date") == null?"":Formatter.datetime2String(resultSet.getDate("a_created_date"));
		authDeletedDate = resultSet.getString("a_deleted_date") == null?"":Formatter.datetime2String(resultSet.getDate("a_career"));
		
		//Set value to object
		user = new User();
		auth = new Authentication();
		
		user.setUserId(userId);
		user.setPassword(password);
		user.setAuthType(authType);
		user.setGender(gender);
		user.setBirthday(birthday);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPreferName(preferName);
		user.setAddress(address);
		user.setCity(city);
		user.setState(state);
		user.setPreferPets(preferPets);
		user.setFeedingPets(feedingPets);
		user.setPetName(petName);
		user.setCareer(career);
		user.setCreatedDate(userCreatedDate);
		user.setDeletedDate(userDeletedDate);
		
		auth.setToken(token);
		auth.setUser(user);
		auth.setCreatedDate(authCreatedDate);
        auth.setDeletedDate(authCreatedDate);		
		return auth;
	}
}
