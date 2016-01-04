package com.petease.app.domain;
public class QA extends QATitle {
	
	//class member
	private String question;
	private String answer;
	
	//constructor
	public QA() {
		super();
		question = "";
		answer = "";
	}
	
	//setter
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	//getter
	public String getQuestion() {
		return this.question;
	}
	public String getAnswer() {
		return this.answer;
	}
	
	//toString
	@Override
	public String toString() {
		return super.toString() +
			   ", question = " + this.question +
			   ", answer = " + this.answer;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof QA)) {
			return false;
		}
		return super.equals(obj) &&
			   (((QA)obj).question.equals(this.question)) &&
			   (((QA)obj).answer.equals(this.answer));
	}
	
	//clone
	@Override
	public QA clone() {
		Object qaParent = super.clone();
		QA qaObj = (QA) qaParent;
		qaObj.question = new String(this.question);
		qaObj.answer = new String(this.answer);
		return qaObj;
	}
}
