package com.petease.app.domain;
public class PostTitle {
    private long postId;
    private String topic;
	private User poster;
	
        //constructor
	PostTitle(){
        this.postId=0;
        this.topic="";
        poster=new User();
    }
	
	//setter
	public void setPostId(long postId) {
		this.postId = postId;
	}
        //getter
	public long getPostId() {
		return this.postId;
	}
        
    public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTopic() {
		return this.topic;
	}
        
	public void setPoster(User poster) {
		this.poster = poster;
	}
    public User getPoster() {
		return this.poster;
	}
	//toString
	@Override
	public String toString() {
		return "postId = " + this.postId +
		       ", topic = " + this.topic +
			   ", poster = [" + this.poster.toString() + "]";
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof PostTitle)) {
			return false;
		}
		PostTitle postTitleObj = (PostTitle) obj;
		return (this.postId == postTitleObj.postId) &&
		       (this.topic.equals(postTitleObj.topic)) &&
		       (this.poster.equals(postTitleObj.poster));
	}
	
	//clone
	@Override
	public PostTitle clone() {
		PostTitle postTitleObj = new PostTitle();
		postTitleObj.postId = new Long(this.postId);
		postTitleObj.topic = new String(this.topic);
		postTitleObj.poster.clone();
		return postTitleObj;
	}
}
  

 