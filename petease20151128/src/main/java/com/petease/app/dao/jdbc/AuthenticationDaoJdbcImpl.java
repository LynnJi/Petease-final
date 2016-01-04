package com.petease.app.dao.jdbc;

import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.petease.app.common.Formatter;
import com.petease.app.dao.AuthenticationDao;
import com.petease.app.dao.UserDao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.User;

@Repository("AuthenticationDaoJdbcImpl")
public class AuthenticationDaoJdbcImpl implements AuthenticationDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private AuthenticationUserRowMapper authUserRowMapper;
	
	@PostConstruct
	public void setup() {
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert=new SimpleJdbcInsert(dataSource);
		jdbcInsert.withTableName("authentication");
		
		authUserRowMapper=new AuthenticationUserRowMapper();
	}
	public void insertToken(Authentication auth) {
		MapSqlParameterSource param=new MapSqlParameterSource();

		param.addValue("token", auth.getToken());
		param.addValue("user_id", auth.getUser().getUserId());
		param.addValue("created_date", new Date());
		
		int rowNo = jdbcInsert.execute(param);
	}
	
	public Authentication selectUserInfoByToken(String token) {
		List<Authentication> authUserList;
		String querySql="SELECT u.user_id AS u_user_id, " + 
		                "u.password AS u_password, " + 
				        "u.auth_type AS u_auth_type, " + 
		                "u.first_name AS u_first_name, " + 
				        "u.last_name AS u_last_name, " + 
				        "u.prefer_name AS u_prefer_name, " + 
		                "u.gender AS u_gender, " + 
				        "u.DOB AS u_DOB, " + 
		                "u.address AS u_address, " + 
				        "u.city AS u_city, " + 
		                "u.state AS u_state, " + 
				        "u.prefer_pets AS u_prefer_pets, " + 
		                "u.feeding_pets AS u_feeding_pets, " + 
				        "u.pet_name AS u_pet_name, " + 
		                "u.career AS u_career, " + 
				        "u.created_date AS u_created_date, " + 
		                "u.deleted_date AS u_deleted_date, " + 
				        "a.token AS a_token, " + 
		                "a.created_date AS a_created_date, " + 
				        "a.deleted_date AS a_deleted_date" + 
		                " FROM user u INNER JOIN authentication a ON u.user_id = a.user_id" + 
				        " WHERE a.token = :token";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("token", token);
		authUserList=dbTemplate.query(querySql, param, authUserRowMapper);
		if(authUserList.size()!=1)return null;
		return authUserList.get(0);
	}	

	public void deleteToken(String token) {
		String deleteStatement = "DELETE FROM authentication WHERE token=:token";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("token", token);
		dbTemplate.update(deleteStatement, param);
	}
}
