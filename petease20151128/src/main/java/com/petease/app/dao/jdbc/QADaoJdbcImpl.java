package com.petease.app.dao.jdbc;

import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.petease.app.dao.QADao;
import com.petease.app.domain.QA;

@Repository("QADaoJdbcImpl")
public class QADaoJdbcImpl implements QADao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private QARowMapper qaRowMapper;
	
	@PostConstruct
	public void setup()
	{
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert=new SimpleJdbcInsert(dataSource);
		jdbcInsert.withTableName("qa");
		jdbcInsert.usingGeneratedKeyColumns("qa_id");
		
		qaRowMapper=new QARowMapper();
	}
	public void insertQuestion(QA qa)
	{
		MapSqlParameterSource param=new MapSqlParameterSource();
		
		param.addValue("user_id", qa.getAsker().getUserId());
		param.addValue("question_title", qa.getTopic());
		param.addValue("q_content", qa.getQuestion());
		
		Number newId=jdbcInsert.executeAndReturnKey(param);
		qa.setQaId(newId.longValue());
	}
	@Override
	public void updateAnswer(QA qa) {
		// TODO Auto-generated method stub
		int updateRows;
		String updateSql="update Answer set ANSWER=:answer where QAID=:qaId";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("qaId", qa.getQaId());
		//param.put("asker",qa.getAsker());
		//param.put("topic",qa.getTopic());
		//param.put("admin",qa.getAdmin());
		//param.put("question",qa.getQuestion());
		param.put("answer", qa.getAnswer());
		
		updateRows=dbTemplate.update(updateSql, param);
	}
	@Override
	public QA selectQAById(long qaId) {
		// TODO Auto-generated method stub
		return null;
	}
}
