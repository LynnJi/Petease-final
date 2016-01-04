package com.petease.app.dao;

import java.util.List;

import com.petease.app.domain.Comment;
import com.petease.app.domain.Post;
import com.petease.app.domain.QA;
import com.petease.app.domain.Rate;

public interface CommentDao {
	public void insertComment(Comment comment);
	public List<Comment> selectCommentListByPageNo(int pageNo, long postId);
}
