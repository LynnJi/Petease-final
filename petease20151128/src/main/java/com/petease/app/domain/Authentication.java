package com.petease.app.domain;
public class Authentication {
	
	//class member
	private String token;
	private User user;
    private String createdDate;
    private String deletedDate;
	
	//constructor
	public Authentication(){
		this.token = "logout";
		this.user = new User();
		this.createdDate = "";
		this.deletedDate = "";
	}
	
	//setter
    public void setToken(String token) {
		this.token = token;
	}
	public String getToken() {
		return this.token;
	}
    public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return this.user;
	}
    public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedDate() {
		return this.createdDate;
	}
    public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	public String getDeletedDate() {
		return this.deletedDate;
	}
	
	//toString
	@Override
	public String toString() {
		return "token = " + this.token +
			   ", user = [" + this.user.toString() + "]" +
               ", createdDate = " + this.createdDate +
               ", deletedDate = " + this.deletedDate ;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Authentication)) {
			return false;
		}
		Authentication userObj = (Authentication) obj;
		return (this.token.equals(userObj.token)) &&
			   (this.user.equals(userObj.user)) &&
               (this.createdDate.equals(userObj.createdDate)) &&
               (this.deletedDate.equals(userObj.deletedDate)) ;
	}
	
	//clone
	@Override
	public Authentication clone() {
		Authentication userObj = new Authentication();
		userObj.token = new String(this.token);
		userObj.user = this.user.clone();
        userObj.createdDate = new String(this.createdDate);
        userObj.deletedDate =new String(this.deletedDate);
		
		return userObj;
	}
}
