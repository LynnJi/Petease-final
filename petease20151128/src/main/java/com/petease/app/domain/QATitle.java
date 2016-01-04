package com.petease.app.domain;
public class QATitle {
	
	//class member
	private long qaId;
	private User asker;
	private String topic;
	private User admin;
	
	//constructor
	public QATitle(){
		this.qaId = 0;
		this.asker = new User();
		this.topic = "";
		this.admin = new User();
	}
	
	//setter
	public void setQaId(long qaId) {
		this.qaId = qaId;
	}
	public void setAsker(User asker) {
		this.asker = asker;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	//getter
	public long getQaId() {
		return this.qaId;
	}
	public User getAsker() {
		return this.asker;
	}
	public String getTopic() {
		return this.topic;
	}
	public User getAdmin() {
		return this.admin;
	}
	
	//toString
	@Override
	public String toString() {
		return "qaId = " + this.qaId +
			   ", asker = [" + this.asker.toString() + "]" +
			   ", topic = " + this.topic +
			   ", admin = [" + this.admin.toString() + "]";
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof QATitle)) {
			return false;
		}
		QATitle qaTitleObj = (QATitle) obj;
		return (this.qaId == qaTitleObj.qaId) &&
			   (this.asker.equals(qaTitleObj.asker)) &&
			   (this.topic.equals(qaTitleObj.topic)) &&
			   (this.admin.equals(qaTitleObj.admin));
	}
	
	//clone
	@Override
	public QATitle clone() {
		QATitle qaTitleObj = new QATitle();
		qaTitleObj.qaId = new Long(this.qaId);
		qaTitleObj.asker.clone();
		qaTitleObj.topic = new String(this.topic);
		qaTitleObj.admin.clone();
		return qaTitleObj;
	}
}
