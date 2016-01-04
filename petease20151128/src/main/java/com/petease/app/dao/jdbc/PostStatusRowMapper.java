package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;
import com.petease.app.domain.User;

public class PostStatusRowMapper implements RowMapper<PostStatus>{
	public PostStatus mapRow(ResultSet resultSet,int row) throws SQLException
	{
		long statusId;
	    long postId;
	    int rate;
	    int viewCount;
	    int commentCount;
	    
	    PostStatus postStatus;    
    
		statusId = resultSet.getLong("status_id");
		postId = resultSet.getLong("post_id");
		rate = resultSet.getInt("rate");
		viewCount = resultSet.getInt("view_count");
		commentCount = resultSet.getInt("comments_count");
		
		postStatus = new PostStatus();
		
		postStatus.setStatusId(statusId);
		postStatus.setPostId(postId);
		postStatus.setRate(rate);
		postStatus.setViewCount(viewCount);
		postStatus.setCommentCount(commentCount);
		
		return postStatus;
	}
}
