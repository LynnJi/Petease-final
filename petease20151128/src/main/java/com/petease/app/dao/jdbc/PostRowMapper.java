package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.Post;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;

public class PostRowMapper implements RowMapper<Post>{
	public Post mapRow(ResultSet resultSet,int row) throws SQLException
	{
	    long postId;
	    String topic;
		User poster;
		String content;
		String picUrl;
		String createdDate;
		boolean reported;
		String reportedDate;
		User commentAdmin;
		String status;
		String deletedDate;
		int rate;
		int viewCount;
		int commentCount;
		
		Post post;;
		
		//Get value from result set of query
		postId = resultSet.getLong("p_post_id");
		topic = resultSet.getString("p_topic") == null?"":resultSet.getString("p_topic");
		
		poster = new User();
		poster.setUserId(resultSet.getString("u_user_id") == null?"":resultSet.getString("u_user_id"));
		poster.setPassword(resultSet.getString("u_password") == null?"":resultSet.getString("u_password"));
		poster.setAuthType(resultSet.getString("u_auth_type") == null?"":resultSet.getString("u_auth_type"));
		poster.setGender(resultSet.getString("u_gender") == null?"":resultSet.getString("u_gender"));
		poster.setBirthday(resultSet.getDate("u_birthday") == null?"":Formatter.date2String(resultSet.getDate("u_birthday")));
		poster.setFirstName(resultSet.getString("u_first_name") == null?"":resultSet.getString("u_first_name"));
		poster.setLastName(resultSet.getString("u_last_name") == null?"":resultSet.getString("u_last_name"));
		poster.setPreferName(resultSet.getString("u_prefer_name") == null?"":resultSet.getString("u_prefer_name"));
		poster.setAddress(resultSet.getString("u_address") == null?"":resultSet.getString("u_address"));
		poster.setCity(resultSet.getString("u_city") == null?"":resultSet.getString("u_city"));
		poster.setState(resultSet.getString("u_state") == null?"":resultSet.getString("u_state"));
		poster.setPreferPets(resultSet.getString("u_prefer_pets") == null?"":resultSet.getString("u_prefer_pets"));
		poster.setFeedingPets(resultSet.getString("u_feeding_pets") ==  null?"":resultSet.getString("u_feeding_pets"));
		poster.setPetName(resultSet.getString("u_pet_name") == null?"":resultSet.getString("u_pet_name"));
		poster.setCareer(resultSet.getString("u_career") == null?"":resultSet.getString("u_career"));
		
		content = resultSet.getString("p_content") == null?"":resultSet.getString("p_content");
		picUrl = resultSet.getString("p_pic_url") == null?"":resultSet.getString("p_pic_url");
		createdDate = resultSet.getDate("p_created_date") == null?"":Formatter.datetime2String(resultSet.getDate("p_created_date"));
		reported = resultSet.getShort("p_reported") == 0?false:true;
		reportedDate = resultSet.getDate("p_reported_date") == null?"":Formatter.datetime2String(resultSet.getDate("p_reported_date"));
		
		commentAdmin = new User();
		commentAdmin.setUserId(resultSet.getString("a_user_id") == null?"":resultSet.getString("a_user_id"));
		commentAdmin.setPassword(resultSet.getString("a_password") == null?"":resultSet.getString("a_password"));
		commentAdmin.setAuthType(resultSet.getString("a_auth_type") == null?"":resultSet.getString("a_auth_type"));
		commentAdmin.setGender(resultSet.getString("a_gender") == null?"":resultSet.getString("a_gender"));
		commentAdmin.setBirthday(resultSet.getDate("a_birthday") == null?"":Formatter.date2String(resultSet.getDate("a_birthday")));
		commentAdmin.setFirstName(resultSet.getString("a_first_name") == null?"":resultSet.getString("a_first_name"));
		commentAdmin.setLastName(resultSet.getString("a_last_name") == null?"":resultSet.getString("a_last_name"));
		commentAdmin.setPreferName(resultSet.getString("a_prefer_name") == null?"":resultSet.getString("a_prefer_name"));
		commentAdmin.setAddress(resultSet.getString("a_address") == null?"":resultSet.getString("a_address"));
		commentAdmin.setCity(resultSet.getString("a_city") == null?"":resultSet.getString("a_city"));
		commentAdmin.setState(resultSet.getString("a_state") == null?"":resultSet.getString("a_state"));
		commentAdmin.setPreferPets(resultSet.getString("a_prefer_pets") == null?"":resultSet.getString("a_prefer_pets"));
		commentAdmin.setFeedingPets(resultSet.getString("a_feeding_pets") == null?"":resultSet.getString("a_feeding_pets"));
		commentAdmin.setPetName(resultSet.getString("a_pet_name") == null?"":resultSet.getString("a_pet_name"));
		commentAdmin.setCareer(resultSet.getString("a_career") == null?"":resultSet.getString("a_career"));
		
		status = resultSet.getString("p_status") == null?"":resultSet.getString("p_status");
		deletedDate = resultSet.getDate("p_deleted_date") == null?"":Formatter.datetime2String(resultSet.getDate("p_deleted_date"));
		rate = resultSet.getInt("ps_rate");
		viewCount = resultSet.getInt("ps_view_count");
		commentCount = resultSet.getInt("ps_comments_count");
		
		//Set value to object
		post = new Post();
		
		post.setPostId(postId);
		post.setTopic(topic);
		post.setPoster(poster);
		post.setContent(content);
		post.setPicUrl(picUrl);
		post.setCreatedDate(createdDate);
		post.setReported(reported);
		post.setReportedDate(reportedDate);
		post.setCommentAdmin(commentAdmin);
		post.setStatus(status);
		post.setDeletedDate(deletedDate);
		post.setRate(rate);
		post.setViewCount(viewCount);
		post.setCommentount(commentCount);
		
		return post;
	}
}
