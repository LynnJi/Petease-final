package com.petease.app.dao;

import java.util.List;

import com.petease.app.domain.Interaction;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;

public interface PostDao {
	public void insertArticle(Post article);
	public List<Post> selectArticleListByPageNo(int pageNo);
	public Post selectArticleByPostId(long postId);
	public void updateViewCountByPostId(long postId);
	public PostStatus selectPostStatus(long postId);
	public void insertInteraction(Interaction interaction);
}
