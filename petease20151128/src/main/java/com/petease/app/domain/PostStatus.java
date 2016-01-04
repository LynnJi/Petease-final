package com.petease.app.domain;
public class PostStatus {
	private long statusId;
	private long postId;
	private int rate;
	private int viewCount;
	private int commentCount;
	
	public PostStatus(){
		statusId = 0;
		postId = 0;
		rate = 0;
		viewCount = 0;
		commentCount = 0;
	}
	
	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}
	public long getStatusId() {
		return this.statusId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public long getPostId() {
		return this.postId;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getRate() {
		return this.rate;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getViewCount() {
		return this.viewCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getCommentCount() {
		return this.commentCount;
	}
	
	public String toString() {
		return "statusId = " + this.statusId +
			   ", postId = " + this.postId +
			   ", rate = " + this.rate +
			   ", viewCount = " + this.viewCount +
		       ", commentCount = " + this.commentCount;
	}
}