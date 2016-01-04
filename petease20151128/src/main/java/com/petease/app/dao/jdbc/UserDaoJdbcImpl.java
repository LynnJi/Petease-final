package com.petease.app.dao.jdbc;

import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.petease.app.common.Formatter;
import com.petease.app.dao.UserDao;
import com.petease.app.domain.User;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private UserRowMapper userRowMapper;
	
	@PostConstruct
	public void setup() {
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert=new SimpleJdbcInsert(dataSource);
		jdbcInsert.withTableName("user");
		
		userRowMapper=new UserRowMapper();
	}
	public void insertUser(User user) throws DuplicateKeyException{
		MapSqlParameterSource param=new MapSqlParameterSource();
		
		param.addValue("user_id", user.getUserId());
		param.addValue("password", user.getPassword());
		param.addValue("auth_type", user.getAuthType());
		param.addValue("first_name", user.getFirstName());
		param.addValue("last_name", user.getLastName());
		param.addValue("prefer_name", user.getPreferName());
		param.addValue("gender", user.getGender());
		param.addValue("DOB", Formatter.string2Date(user.getBirthday()));
		param.addValue("address", user.getAddress());
		param.addValue("city", user.getCity());
		param.addValue("state", user.getState());
		param.addValue("prefer_pets", user.getPreferPets());
		param.addValue("feeding_pets", Integer.parseInt(user.getFeedingPets()));
		param.addValue("pet_name", user.getPetName());
		param.addValue("career", user.getCareer());
		param.addValue("created_date", new Date());
		
		int rowNo = jdbcInsert.execute(param);
	}
	
	public User selectUserById(String userId) {
		List<User> userList;
		String querySql="SELECT * FROM user WHERE user_id=:userId";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("userId", userId);
		userList=dbTemplate.query(querySql, param, userRowMapper);
		if(userList.size()!=1)return null;
		return userList.get(0);
	}	
}
