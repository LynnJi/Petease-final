package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;

public class QARowMapper implements RowMapper<QA>{
	public QA mapRow(ResultSet resultSet,int row) throws SQLException
	{
	    long qaId;
		User asker;
		String topic;
		User admin;
		String question;
		String answer;
		
		QA qa;
		
		//Get value from result set of query
		qaId = resultSet.getLong("qa_id");
		
		asker = new User();
		asker.setUserId(resultSet.getString("u_user_id"));
		asker.setPassword(resultSet.getString("u_password"));
		asker.setAuthType(resultSet.getString("u_auth_type"));
		asker.setGender(resultSet.getString("u_gender"));
		asker.setBirthday(Formatter.date2String(resultSet.getDate("u_birthday")));
		asker.setFirstName(resultSet.getString("u_first_name"));
		asker.setLastName(resultSet.getString("u_last_name"));
		asker.setPreferName(resultSet.getString("u_prefer_name"));
		asker.setAddress(resultSet.getString("u_address"));
		asker.setCity(resultSet.getString("u_city"));
		asker.setState(resultSet.getString("u_state"));
		asker.setPreferPets(resultSet.getString("u_prefer_pets"));
		asker.setFeedingPets(resultSet.getString("u_feeding_pets"));
		asker.setPetName(resultSet.getString("u_pet_name"));
		asker.setCareer(resultSet.getString("u_career"));
		
		topic = resultSet.getString("topic");
		
		admin = new User();
		admin.setUserId(resultSet.getString("a_user_id"));
		admin.setPassword(resultSet.getString("a_password"));
		admin.setAuthType(resultSet.getString("a_auth_type"));
		admin.setGender(resultSet.getString("a_gender"));
		admin.setBirthday(Formatter.date2String(resultSet.getDate("a_birthday")));
		admin.setFirstName(resultSet.getString("a_first_name"));
		admin.setLastName(resultSet.getString("a_last_name"));
		admin.setPreferName(resultSet.getString("a_prefer_name"));
		admin.setAddress(resultSet.getString("a_address"));
		admin.setCity(resultSet.getString("a_city"));
		admin.setState(resultSet.getString("a_state"));
		admin.setPreferPets(resultSet.getString("a_prefer_pets"));
		admin.setFeedingPets(resultSet.getString("a_feeding_pets"));
		admin.setPetName(resultSet.getString("a_pet_name"));
		admin.setCareer(resultSet.getString("a_career"));
		
		question = resultSet.getString("question");
		answer = resultSet.getString("answer");
		
		//Set value to object
		qa = new QA();
		
		qa.setQaId(qaId);
		qa.setAsker(asker);
		qa.setTopic(topic);
		qa.setAdmin(admin);
		qa.setQuestion(question);
		qa.setAnswer(answer);
		
		return qa;
	}
}
