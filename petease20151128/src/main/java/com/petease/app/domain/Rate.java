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
public class Rate {
        //member;
        private long rateId;
        private Post article;
        private User rater;
        private int rate;
        
        //constructor
        public Rate(){
            this.rateId = 0;
            article = new Post();
            rater = new User();
            this.rate = 0;
        }
        
        //getter
        public long getRateId(){
            return this.rateId;
        }
        public Post getArticle(){
            return this.article;
        }
        public User getRater(){
            return this.rater;
        }
        public int getRate(){
            return this.rate;
        }
        
        //setter
        public void setRateId(long rateId){
            this.rateId = rateId;
        }
        public void setArticle(Post article){
            this.article = article;
        }
        public void setRater(User rater){
            this.rater = rater;
        }
        public void setRate(int rate){
            this.rate = rate;
        }
        
        //toString
	@Override
	public String toString() {
		return "rateId = " + this.rateId +
			   ", article = [" + this.article.toString() + "]" +
			   ", rate = " + this.rate +
			   ", rater = [" + this.rater.toString() + "]";
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Rate)) {
			return false;
		}
		Rate rateObj = (Rate) obj;
		return (this.rateId == rateObj.rateId) &&
			   (this.article.equals(rateObj.article)) &&
			   (this.rate == rateObj.rate) &&
			   (this.rater.equals(rateObj.rater));
	}
	
	//clone
	@Override
	public Rate clone() {
		Rate rateObj = new Rate();
		rateObj.rateId = new Long(this.rateId);
		rateObj.article.clone();
		rateObj.rate = new Integer(this.rate);
		rateObj.rater.clone();
		return rateObj;
	}
}
