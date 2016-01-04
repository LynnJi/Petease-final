/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.petease.app.domain;

/**
 *
 * @author Raymond_yl
 */
public class Comment {
        //member
        private long commentId;
        private Post article;
        private User commenter;
        private String comment;
        private String createDate;
        private String deleteDate;
        private String status;
        
        //Constructor
        public Comment(){
            this.commentId = 0;
            article = new Post();
            commenter = new User();
            this.comment = "";
            this.createDate = "";
            this.deleteDate = "";
            this.status = "";
        }
        
        //getter
        public long getCommentId(){
            return this.commentId;
        }
        public Post getArticle(){
            return this.article;
        }
        public User getCommenter(){
            return this.commenter;
        }
        public String getComment(){
            return this.comment;
        }
        public String getCreateDate(){
            return this.createDate;
        }
        public String getDeleteDate(){
            return this.deleteDate;
        }
        public String getStatus(){
            return this.status;
        }
        
        //getter
        public void setCommentId(long commentId){
            this.commentId = commentId;
        }
        public void setArticle(Post article){
            this.article = article;
        }
        public void setCommenter(User commenter){
            this.commenter = commenter;
        }
        public void setComment(String comment){
            this.comment = comment;
        }
        public void setCreateDate(String createDate){
            this.createDate = createDate;
        }
        public void setDeleteDate(String deleteDate){
            this.deleteDate = deleteDate;
        }
        public void setStatus(String status){
            this.status = status;
        }
        
        //toString
	@Override
	public String toString() {
		return "commentId = " + this.commentId +
			   ", article = [" + this.article.toString() + "]" +
			   ", commenter = [" + this.commenter.toString() + "]" +
                           ", comment = " + this.comment +
                           ", createDate = " + this.createDate +
                           ", deleteDate = " + this.deleteDate +
                           ", status = " + this.status;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Comment)) {
			return false;
		}
		Comment commentObj = (Comment) obj;
		return (this.commentId == commentObj.commentId) &&
			   (this.article.equals(commentObj.article)) &&
			   (this.commenter.equals(commentObj.commenter)) &&
                           (this.comment.equals(commentObj.comment)) &&
                           (this.createDate.equals(commentObj.createDate)) &&
                           (this.deleteDate.equals(commentObj.deleteDate)) &&
			   (this.status.equals(commentObj.status));
	}
	
	//clone
	@Override
	public Comment clone() {
		Comment commentObj = new Comment();
		commentObj.commentId = new Long(this.commentId);
		commentObj.article.clone();
                commentObj.commenter.clone();
		commentObj.comment = new String(this.comment);
                commentObj.createDate = new String(this.createDate);
                commentObj.deleteDate = new String(this.deleteDate);
                commentObj.status = new String(this.status);
		return commentObj;
	}
}
