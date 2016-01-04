package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.Comment;
import com.petease.app.domain.Post;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;
import com.petease.app.domain.User;

public class CommentRowMapper implements RowMapper<Comment>{
	public Comment mapRow(ResultSet resultSet,int row) throws SQLException
	{
		long commentId;
	    long postId;
	    String userId;
	    String preferName;
	    String comment;
	    String createDate;
	    String deleteDate;
	    String status;
	    
	    Comment commentInstance;
	    User user = new User();
	    Post post = new Post();

		commentId = resultSet.getLong("comment_id");
		postId = resultSet.getLong("post_id");
		userId = resultSet.getString("user_id") == null?"":resultSet.getString("user_id");
		preferName = resultSet.getString("prefer_name") == null?"":resultSet.getString("prefer_name");
		comment = resultSet.getString("comment") == null?"":resultSet.getString("comment");
		createDate = resultSet.getDate("create_date") == null?"":Formatter.datetime2String(resultSet.getTime("create_date"));
		deleteDate = resultSet.getDate("delete_date") == null?"":Formatter.datetime2String(resultSet.getTime("delete_date"));
		status = resultSet.getString("status") == null?"":resultSet.getString("status");
		
		user.setUserId(userId);
		user.setPreferName(preferName);
		post.setPostId(postId);
		
		//Set value to object
		commentInstance = new Comment();
		
		commentInstance.setCommentId(commentId);
		commentInstance.setArticle(post);
		commentInstance.setCommenter(user);
		commentInstance.setComment(comment);
		commentInstance.setCreateDate(createDate);
		commentInstance.setDeleteDate(deleteDate);
		commentInstance.setStatus(status);
		
		return commentInstance;
	}
}
