package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.Post;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;
import com.petease.app.domain.User;

public class RateRowMapper implements RowMapper<Rate>{
	public Rate mapRow(ResultSet resultSet,int row) throws SQLException
	{
		long rateId;
	    long postId;
	    String userId;
	    int rate;
	    
	    Rate rateInstance;
	    User user = new User();
	    Post post = new Post();

		rateId = resultSet.getLong("rate_id");
		postId = resultSet.getLong("post_id");
		userId = resultSet.getString("user_id") == null?"":resultSet.getString("user_id");
		rate = resultSet.getInt("rate");
		
		user.setUserId(userId);
		post.setPostId(postId);
		
		//Set value to object
		rateInstance = new Rate();
		
		rateInstance.setRateId(rateId);
		rateInstance.setRate(rate);
		rateInstance.setRater(user);
		rateInstance.setArticle(post);
		
		return rateInstance;
	}
}
