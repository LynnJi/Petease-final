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
public class NewsTitle {
    //class member
    private long newsId;
    private User admin;;
    private String topic;
    
    //constructor
    public NewsTitle(){
        this.newsId = 0;
        this.admin = new User();
        this.topic = "";
    }
    
    //getter
    public long getNewsId(){
        return this.newsId;
    }
    public User getAdmin(){
        return this.admin;
    }
    public String getTopic(){
        return this.topic;
    }
    //setter
    public void setNewsId(long newsId){
        this.newsId = newsId;
    }
    public void setAdmin(User admin){
        this.admin = admin;
    }
    public void setTopic(String topic){
        this.topic = topic;
    }
    //toString
    @Override
        public String toString(){
                return "newsId = "+ this.newsId +
                       ", admin = [" + this.admin.toString() + "]" +
                       ", topic = " + this.topic ;
        }
    
    //equals
    @Override
        public boolean equals(Object obj){
                if(!(obj instanceof NewsTitle)) {
            		return false;
                }
                NewsTitle newsTitleObj = (NewsTitle) obj;
                return (this.newsId == newsTitleObj.newsId) &&
            		(this.topic.equals(newsTitleObj.topic)) &&
            		(this.admin.equals(newsTitleObj.admin));    
        }
    
    @Override
	public NewsTitle clone() {
		NewsTitle newsTitleObj = new NewsTitle();
		newsTitleObj.newsId = new Long(this.newsId);
		newsTitleObj.topic = new String(this.topic);
		newsTitleObj.admin.clone();
		return newsTitleObj;
	}
}
