package com.petease.app.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.petease.app.common.Formatter;
import com.petease.app.domain.News;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;

public class NewsRowMapper implements RowMapper<News>{
	public News mapRow(ResultSet resultSet,int row) throws SQLException
	{
	    long newsId;
		User admin;
		String topic;
		String content;
		String picUrl;
        String createDate;
        String deleteDate;
		
		News news;
		
		//Get value from result set of query
		newsId = resultSet.getLong("news_id");
		
		topic = resultSet.getString("news_title") == null?"":resultSet.getString("news_title");
		
		admin = new User();
		admin.setUserId(resultSet.getString("admin_id") == null?"":resultSet.getString("admin_id"));
		
		content = resultSet.getString("content") == null?"":resultSet.getString("content");
		picUrl = resultSet.getString("pic_url") == null?"":resultSet.getString("pic_url");
		createDate = resultSet.getDate("created_date") == null?"":Formatter.datetime2String(resultSet.getDate("created_date"));
		deleteDate = resultSet.getDate("deleted_date") == null?"":Formatter.datetime2String(resultSet.getDate("deleted_date"));
		
		//Set value to object
		news = new News();
		
		news.setNewsId(newsId);
		news.setAdmin(admin);
		news.setTopic(topic);
		news.setContent(content);
		news.setPicUrl(picUrl);
		news.setCreateDate(createDate);
		news.setDeleteDate(deleteDate);
		
		return news;
	}
}
