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

import com.petease.app.dao.PostDao;
import com.petease.app.dao.QADao;
import com.petease.app.dao.RateDao;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;

@Repository("RateDaoJdbcImpl")
public class RateDaoJdbcImpl implements RateDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsertRate;
	private RateRowMapper rateRowMapper;
	private PostStatusRowMapper postStatusRowMapper;
	
	@PostConstruct
	public void setup()
	{
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsertRate=new SimpleJdbcInsert(dataSource);
		jdbcInsertRate.withTableName("rate");
		jdbcInsertRate.usingGeneratedKeyColumns("rate_id");
		
		rateRowMapper=new RateRowMapper();
		postStatusRowMapper = new PostStatusRowMapper();
	}
	public void insertRate(Rate rate)
	{
		int updateRows;
	    
		String updateStatement = "UPDATE post_status SET post_status.rate=" +
                                     "(SELECT (" +
                                           "IFNULL(SUM(rate),0) + :rate)/(" +
                                                "SELECT COUNT(0) FROM rate WHERE post_id=:postId" +
		                                   ") FROM rate WHERE rate.post_id=:postId" +
	                                 ")" +
                                  "WHERE post_status.post_id=:postId";

		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("rate", rate.getRate());
		param.put("postId", rate.getArticle().getPostId());
		updateRows=dbTemplate.update(updateStatement, param);
	}
}
