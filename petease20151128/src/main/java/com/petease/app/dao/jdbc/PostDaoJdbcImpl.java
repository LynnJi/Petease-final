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
import com.petease.app.domain.Interaction;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;

@Repository("PostDaoJdbcImpl")
public class PostDaoJdbcImpl implements PostDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsertPost;
	private SimpleJdbcInsert jdbcInsertPostStatus;
	private SimpleJdbcInsert jdbcInsertRate;
	private SimpleJdbcInsert jdbcInsertComment;
	private PostRowMapper postRowMapper;
	private PostStatusRowMapper postStatusRowMapper;
	
	@PostConstruct
	public void setup()
	{
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsertPost=new SimpleJdbcInsert(dataSource);
		jdbcInsertPostStatus=new SimpleJdbcInsert(dataSource);
		jdbcInsertRate=new SimpleJdbcInsert(dataSource);
		jdbcInsertComment=new SimpleJdbcInsert(dataSource);
		jdbcInsertPost.withTableName("post");
		jdbcInsertPost.usingGeneratedKeyColumns("post_id");
		jdbcInsertPostStatus.withTableName("post_status");
		jdbcInsertPostStatus.usingGeneratedKeyColumns("status_id");
		jdbcInsertRate.withTableName("rate");
		jdbcInsertRate.usingGeneratedKeyColumns("rate_id");
		jdbcInsertComment.withTableName("comment");
		jdbcInsertComment.usingGeneratedKeyColumns("comment_id");
		
		postRowMapper=new PostRowMapper();
		postStatusRowMapper = new PostStatusRowMapper();
	}
	public void insertArticle(Post article)
	{
		MapSqlParameterSource paramPost=new MapSqlParameterSource();
		MapSqlParameterSource paramPostStatus=new MapSqlParameterSource();
		
		paramPost.addValue("user_id", article.getPoster().getUserId());
		paramPost.addValue("topic", article.getTopic());
		paramPost.addValue("content", article.getContent());
		paramPost.addValue("pic_url", article.getPicUrl());
		paramPost.addValue("created_date", new Date());
		paramPost.addValue("status", article.getStatus());
		
		Number newIdPost=jdbcInsertPost.executeAndReturnKey(paramPost);
		article.setPostId(newIdPost.longValue());
		
		System.out.println("PostDao: " + article.toString());
		paramPostStatus.addValue("post_id", article.getPostId());
		paramPostStatus.addValue("rate", 0);
		paramPostStatus.addValue("view_count", 0);
		paramPostStatus.addValue("comments_count", 0);
		Number newIdPoststutus=jdbcInsertPostStatus.executeAndReturnKey(paramPostStatus);
		

		MapSqlParameterSource paramRate=new MapSqlParameterSource();
		
		paramRate.addValue("user_id", article.getPoster().getUserId());
		paramRate.addValue("post_id", article.getPostId());
		paramRate.addValue("rate", 0);
		
		Number newRateId=jdbcInsertRate.executeAndReturnKey(paramRate);
	}
	public List<Post> selectArticleListByPageNo(int pageNo) {
		List<Post> articleList;
		int countPerPage = 15;
		int countSoFar = countPerPage * (pageNo - 1);
		String queryStatement = "SELECT * FROM post_view " +
							    "WHERE p_post_id>( " +
		                             "SELECT IFNULL(MAX(p_post_id),0) FROM (" +
							               "SELECT p_post_id FROM (" +
		                                         "SELECT p_post_id FROM post_view ORDER BY p_post_id" +
							               ") a LIMIT :countSoFar" +
		                             ") b" +
							    ") ORDER BY p_post_id LIMIT :countPerPage";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("countSoFar", countSoFar);
		param.put("countPerPage", countPerPage);
		articleList=dbTemplate.query(queryStatement, param, postRowMapper);
		return articleList;
	}
	
	public Post selectArticleByPostId(long postId) {
		Post article;
		String queryStatement = "SELECT * FROM post_view " +
							    "WHERE p_post_id=:postId";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("postId", postId);
		article=dbTemplate.query(queryStatement, param, postRowMapper).get(0);
		return article;
	}
	
	public void updateViewCountByPostId(long postId) {
		int updateRows;
		String updateStatement = "UPDATE post_status " +
								 "SET view_count = view_count + 1 " +
								 "WHERE post_id=:postId";

		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("postId", postId);
		updateRows=dbTemplate.update(updateStatement, param);
	}
	
	public PostStatus selectPostStatus(long postId) {
		PostStatus postStatus;
		String queryStatement = "SELECT * FROM post_status WHERE post_id=:postId";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("postId", postId);
		postStatus = dbTemplate.query(queryStatement, param, postStatusRowMapper).get(0);
		return postStatus;
	}
	
	public void insertInteraction(Interaction interaction) {
		MapSqlParameterSource paramRate=new MapSqlParameterSource();
		MapSqlParameterSource paramComment=new MapSqlParameterSource();
		
		paramRate.addValue("user_id", interaction.getRate().getRater().getUserId());
		paramRate.addValue("post_id", interaction.getRate().getArticle().getPostId());
		paramRate.addValue("rate", interaction.getRate().getRate());
		
		Number newRateId=jdbcInsertRate.executeAndReturnKey(paramRate);
		interaction.getRate().setRateId(newRateId.longValue());
		
		
		paramComment.addValue("user_id", interaction.getComment().getCommenter().getUserId());
		paramComment.addValue("post_id", interaction.getComment().getArticle().getPostId());
		paramComment.addValue("comment", interaction.getComment().getComment());
		paramComment.addValue("create_date", new Date());
		paramComment.addValue("status", "SV");
		Number newCommentId=jdbcInsertComment.executeAndReturnKey(paramComment);
		interaction.getComment().setCommentId(newCommentId.longValue());
		
	}
}
