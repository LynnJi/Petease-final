package com.petease.app.service;

import java.util.List;

import com.petease.app.dao.QADao;
import com.petease.app.domain.Comment;
import com.petease.app.domain.Interaction;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;

public interface PostService {
	public void writeArticle(Post article, String token);
	public List<Post> readArticleList(int pageNo);
	public Post readArticleByPostId(long postId);
	public List<Comment> readCommentList(int pageNo, long postId);
	public PostStatus readPostStatus(long postId);
	public void writeCommentRate(Interaction interaction, String token);
}
