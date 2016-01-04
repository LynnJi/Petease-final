package com.petease.app.domain;

import org.springframework.web.multipart.MultipartFile;

public class Post extends PostTitle{
    //class member
	private String content;
	private String picUrl;
	private String createdDate;
    private boolean reported;
	private String reportedDate;
    private User commentAdmin;
    private String status;
    private String deletedDate;
    private int rate;
    private int viewCount;
    private int commentCount;
	
	//constructor
	public Post(){
		  super();
          this.content = "";
          this.picUrl="";
          this.createdDate = "";
          this.reported = false;
          this.reportedDate = "";
          this.commentAdmin = new User();
          this.status = "";
          this.deletedDate = "";
          this.rate= 0;
          this.viewCount=0;
          this.commentCount=0;
    }
	
	//setter
    public void setContent(String content) {
    	this.content= content;
	}
	public String getContent() {
		return this.content;
	}
    public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicUrl() {
		return this.picUrl;
	}
    public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedDate() {
		return this.createdDate;
	}
    public void setReported(boolean reported) {
		this.reported = reported;
	}
	public boolean getReported() {
		return this.reported;
	}
    public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getReportedDate() {
		return this.reportedDate;
	}
    public void setCommentAdmin(User commentAdmin) {
		this.commentAdmin = commentAdmin;
	}
    public User getCommentAdmin() {
		return this.commentAdmin;
	}
    public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
    public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getDeletedDate() {
		return this.deletedDate;
	}
    public void setRate(int rate) {
		this.rate = rate;
	}
	public int getRate() {
		return this.rate;
	}
    public void setViewCount(int viewCount) {
		this.viewCount= viewCount;
	}
	public int getViewCount() {
		return this.viewCount;
	}
    public void setCommentount(int commentCount) {
		this.commentCount= commentCount;
	}
	public int getCommentCount() {
		return this.commentCount;
	}
        
	//toString
	@Override
	public String toString() {
		return super.toString() + 
			   ", content = " + this.content +
			   ", picUrl = " + this.picUrl +
			   ", createdDate = " + this.createdDate +
               ", reported = " + this.reported +
               ", reportedDate = " + this.reportedDate +
               ", commentAdmin = [" + this.commentAdmin.toString() + "]"+
               ", status = " + this.status +
               ", daletedDate = " + this.deletedDate +
               ", rate = " + this.rate +
               ", viewCount = " + this.viewCount +
               ", commentCount = " + this.commentCount ;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Post)) {
			return false;
		}
		Post postObj = (Post) obj;
		return (this.content.equals(postObj.content)) &&
		       (this.picUrl.equals(postObj.picUrl)) &&
		       (this.createdDate.equals(postObj.createdDate)) &&
               (this.reported == postObj.reported) &&
               (this.reportedDate.equals(postObj.reportedDate)) &&
               (this.commentAdmin.equals(postObj.commentAdmin))&&
               (this.status.equals(postObj.status)) &&
               (this.deletedDate.equals(postObj.deletedDate)) &&
               (this.rate == postObj.rate) &&
               (this.viewCount == postObj.viewCount) &&
               (this.commentCount == postObj.commentCount) ;
	}
	
	//clone
	@Override
	public Post clone() {
		Post postObj = new Post();
		postObj.content = new String(this.content);
        postObj.picUrl = new String(this.picUrl);
        postObj.createdDate = new String(this.createdDate);
        postObj.reported = new Boolean(this.reported);
        postObj.reportedDate =new String(this.reportedDate);
        postObj.commentAdmin.clone();
        postObj.status = new String(this.status);
        postObj.deletedDate = new String(this.deletedDate);
        postObj.rate = new Integer(this.rate);
        postObj.viewCount = new Integer(this.viewCount);
        postObj.commentCount =new Integer(this.commentCount);
		
		return postObj;
	}
}