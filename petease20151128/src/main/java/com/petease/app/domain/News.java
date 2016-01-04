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
public class News extends NewsTitle{
        //member
        private String content;
        private String picUrl;
        private String createDate;
        private String deleteDate;
        
        //Constructor
        public News(){
            super();
            this.content = "";
            this.picUrl = "";
            this.createDate = "";
            this.deleteDate = "";
        }
        
        //getter
        public String getContent(){
            return this.content;
        }
        public String getPicUrl(){
            return this.picUrl;
        }
        public String getCreateDate(){
            return this.createDate;
        }
        public String getDeleteDate(){
            return this.deleteDate;
        }
        //setter
        public void setContent(String content){
            this.content = content;
        }
        public void setPicUrl(String picUrl){
            this.picUrl = picUrl;
        }
        public void setCreateDate(String createDate){
            this.createDate = createDate;
        }
        public void setDeleteDate(String deleteDate){
            this.deleteDate = deleteDate;
        }
        
        //toString
	@Override
	public String toString() {
		return super.toString() +
			   ", content = " + this.content +
			   ", picUrl = " + this.picUrl + 
                           ", createDate = " + this.createDate + 
                           ", deleteDate = " + this.deleteDate;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof News)) {
			return false;
		}
		return super.equals(obj) &&
			   (((News)obj).content.equals(this.content)) &&
                           (((News)obj).picUrl.equals(this.picUrl)) &&
                           (((News)obj).createDate.equals(this.createDate)) &&
			   (((News)obj).deleteDate.equals(this.deleteDate));
	}
	
	//clone
	@Override
	public News clone() {
		Object newsParent = super.clone();
		News newsObj = (News) newsParent;
		newsObj.content = new String(this.content);
		newsObj.picUrl = new String(this.picUrl);
                newsObj.createDate = new String(this.createDate);
                newsObj.deleteDate = new String(this.deleteDate);
		return newsObj;
	}
}
