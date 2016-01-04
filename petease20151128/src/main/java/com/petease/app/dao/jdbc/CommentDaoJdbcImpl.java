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

import com.petease.app.dao.CommentDao;
import com.petease.app.dao.PostDao;
import com.petease.app.dao.QADao;
import com.petease.app.dao.RateDao;
import com.petease.app.domain.Comment;
import com.petease.app.domain.Post;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;

@Repository("CommentDaoJdbcImpl")
public class CommentDaoJdbcImpl implements CommentDao{
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsertComment;
	private CommentRowMapper commentRowMapper;
	
	@PostConstruct
	public void setup()
	{
		dbTemplate=new NamedParameterJdbcTemplate(dataSource);
		jdbcInsertComment = new SimpleJdbcInsert(dataSource);
		jdbcInsertComment.withTableName("comment");
		jdbcInsertComment.usingGeneratedKeyColumns("comment_id");
		
		commentRowMapper=new CommentRowMapper();
	}
	public void insertComment(Comment comment)
	{
		MapSqlParameterSource paramComment = new MapSqlParameterSource();
		int updateRows;
		
		paramComment.addValue("user_id", comment.getCommenter().getUserId());
		paramComment.addValue("post_id", comment.getArticle().getPostId());
		paramComment.addValue("comment", comment.getComment());
		paramComment.addValue("create_date", comment.getCreateDate());
		paramComment.addValue("status", comment.getStatus());
		
		Number newIdComment = jdbcInsertComment.executeAndReturnKey(paramComment);
		comment.setCommentId(newIdComment.longValue());
	    
		String updateStatement = "UPDATE post_status " +
								 "SET post_status.comments_count=" +
								 "(SELECT COUNT(0) FROM comment WHERE post_id=:postId)" +
								 "WHERE post_id=:postId";

		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("postId", comment.getArticle().getPostId());
		updateRows=dbTemplate.update(updateStatement, param);
	}
	
	public List<Comment> selectCommentListByPageNo(int pageNo, long postId) {
		List<Comment> commentList;
		int countPerPage = 10;
		int countSoFar = countPerPage * (pageNo - 1);
		String queryStatement = "SELECT * FROM comment_view " +
								"WHERE post_id=:postId AND " +
				                      "comment_id>(" +
								            "SELECT IFNULL(MAX(comment_id),0) FROM (" +
				                                  "SELECT comment_id  FROM (" +
								                       "SELECT comment_id FROM comment_view ORDER BY comment_id" +
								                  ") a LIMIT :countSoFar" +
								            ") b" +
							          ") ORDER BY comment_id LIMIT :countPerPage";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("countSoFar", countSoFar);
		param.put("countPerPage", countPerPage);
		param.put("postId", postId);
		commentList=dbTemplate.query(queryStatement, param, commentRowMapper);
		return commentList;
	}
}
