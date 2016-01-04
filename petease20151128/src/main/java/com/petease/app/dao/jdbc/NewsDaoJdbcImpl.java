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

import com.petease.app.dao.NewsDao;
import com.petease.app.dao.QADao;
import com.petease.app.domain.News;
import com.petease.app.domain.QA;

@Repository("NewsDaoJdbcImpl")
public class NewsDaoJdbcImpl implements NewsDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private NewsRowMapper newsRowMapper;
	
	@PostConstruct
	public void setup()
	{
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		
		newsRowMapper=new NewsRowMapper();
	}

	public List<News> selectAllNews() {
		List<News> newsList;
		String selectStatement = "SELECT * FROM news";
		newsList = dbTemplate.query(selectStatement, newsRowMapper);
		return newsList;
	}
	
	public News selectctNewsByNewsId(long newsId) {
		News news;
		String selectStatement = "SELECT * FROM news WHERE news_id=:newsId";
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("newsId", newsId);
		news = dbTemplate.query(selectStatement, param, newsRowMapper).get(0);
		return news;
	}
}
