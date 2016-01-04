package com.petease.app.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petease.app.common.Formatter;
import com.petease.app.dao.AuthenticationDao;
import com.petease.app.dao.CommentDao;
import com.petease.app.dao.PostDao;
import com.petease.app.dao.QADao;
import com.petease.app.dao.RateDao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.Comment;
import com.petease.app.domain.Interaction;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;


@Service("PostServiceImpl")
@Transactional
public class PostServiceImpl implements PostService {
	//Dependency injection
	@Autowired
	@Qualifier("PostDaoJdbcImpl")
	private PostDao postDao;
	@Autowired
	@Qualifier("AuthenticationDaoJdbcImpl")
	private AuthenticationDao authDao;
	@Autowired
	@Qualifier("CommentDaoJdbcImpl")
	private CommentDao commentDao;
	@Autowired
	@Qualifier("RateDaoJdbcImpl")
	private RateDao rateDao;
	
	
	public void writeArticle(Post article, String token) {
		Authentication auth = authDao.selectUserInfoByToken(token);
		article.setPoster(auth.getUser());
		postDao.insertArticle(article);
	}
	public List<Post> readArticleList(int pageNo) {
		return postDao.selectArticleListByPageNo(pageNo);
	}
	public Post readArticleByPostId(long postId) {
		postDao.updateViewCountByPostId(postId);
		return postDao.selectArticleByPostId(postId);
	}
	public List<Comment> readCommentList(int pageNo, long postId) {
		return commentDao.selectCommentListByPageNo(pageNo, postId);
	}
	public PostStatus readPostStatus(long postId) {
		return postDao.selectPostStatus(postId);
	}
	public void writeCommentRate(Interaction interaction, String token) {
		Authentication auth = authDao.selectUserInfoByToken(token);
		interaction.getComment().setCommenter(auth.getUser());
		interaction.getRate().setRater(auth.getUser());
		postDao.insertInteraction(interaction);
		rateDao.insertRate(interaction.getRate());
	}
}