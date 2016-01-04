package com.petease.app.domain;
public class User {
	
	//class member
	private String userId;
	private String password;
	private String authType;
	private String firstName;
	private String lastName;
	private String preferName;
    private String gender;
    private String birthdayYear;
    private String birthdayMonth;
    private String birthdayDay;
    private String birthday;
    private String address;
    private String city;
    private String state;
    private String preferPets;
    private String feedingPets;
    private String petName;
    private String career;
    private String createdDate;
    private String deletedDate;
	
	//constructor
	public User(){
		this.userId = "";
		this.password = "";
		this.authType = "";
		this.firstName = "";
		this.lastName = "";
		this.preferName = "";
		this.gender = "";
		this.birthdayYear = "";
		this.birthdayMonth = "";
		this.birthdayDay = "";
		this.birthday = "";
		this.address = "";
		this.city = "";
		this.state = "";
		this.preferPets = "";
		this.feedingPets = "";
		this.petName = "";
		this.career = "";
		this.createdDate = "";
		this.deletedDate = "";
	}
	
	//setter
    public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId() {
		return this.userId;
	}
    public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
    public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthType() {
		return this.authType;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return this.firstName;
	}
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return this.lastName;
	}
    public void setPreferName(String preferName) {
		this.preferName = preferName;
	}
	public String getPreferName() {
		return this.preferName;
	}
    public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}
    public void setBirthdayYear(String birthdayYear) {
		this.birthdayYear = birthdayYear;
	}
	public String getBirthdayYear() {
		return this.birthdayYear;
	}
    public void setBirthdayMonth(String birthdayMonth) {
		this.birthdayMonth = birthdayMonth;
	}
	public String getBirthdayMonth() {
		return this.birthdayMonth;
	}
    public void setBirthdayDay(String birthdayDay) {
		this.birthdayDay = birthdayDay;
	}
	public String getBirthdayDay() {
		return this.birthdayDay;
	}
    public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return this.birthday;
	}
    public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}
    public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return this.city;
	}
    public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return this.state;
	}
    public void setPreferPets(String preferPets) {
		this.preferPets = preferPets;
	}
	public String getPreferPets() {
		return this.preferPets;
	}
    public void setFeedingPets(String feedingPets) {
		this.feedingPets = feedingPets;
	}
	public String getFeedingPets() {
		return this.feedingPets;
	}
    public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetName() {
		return this.petName;
	}
    public void setCareer(String career) {
		this.career = career;
	}
	public String getCareer() {
		return this.career;
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
		return "userId = " + this.userId +
			   ", password = " + this.password +
			   ", authType = " + this.authType +
               ", firstName = " + this.firstName +
               ", lastName = " + this.lastName +
               ", preferName = " + this.preferName +
               ", gender = " + this.gender +
               ", birthdayYear = " + this.birthdayYear +
               ", birthdayMonth = " + this.birthdayMonth +
               ", birthdayDay = " + this.birthdayDay +
               ", birthday = " + this.birthday +
               ", address = " + this.address +
               ", city = " + this.city +
               ", state = " + this.state +
               ", preferPets = " + this.preferPets +
               ", feedingPets = " + this.feedingPets +
               ", petName = " + this.petName +
               ", career = " + this.career +
               ", createdDate = " + this.createdDate +
               ", deletedDate = " + this.deletedDate ;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof User)) {
			return false;
		}
		User userObj = (User) obj;
		return (this.userId.equals(userObj.userId)) &&
		       (this.password.equals(userObj.password)) &&
		       (this.authType.equals(userObj.authType)) &&
               (this.firstName.equals(userObj.firstName)) &&
               (this.lastName.equals(userObj.lastName)) &&
               (this.preferName.equals(userObj.preferName)) &&
               (this.gender.equals(userObj.gender)) &&
               (this.birthdayYear.equals(userObj.birthdayYear)) &&
               (this.birthdayMonth.equals(userObj.birthdayMonth)) &&
               (this.birthdayDay.equals(userObj.birthdayDay)) &&
               (this.birthday.equals(userObj.birthday)) &&
               (this.address.equals(userObj.address)) &&
               (this.city.equals(userObj.city)) &&
               (this.state.equals(userObj.state)) &&
               (this.preferPets.equals(userObj.preferPets)) &&
               (this.feedingPets.equals(userObj.feedingPets)) &&
               (this.petName.equals(userObj.petName)) &&
               (this.career.equals(userObj.career)) &&
               (this.createdDate.equals(userObj.createdDate)) &&
               (this.deletedDate.equals(userObj.deletedDate)) ;
	}
	
	//clone
	@Override
	public User clone() {
		User userObj = new User();
		userObj.userId = new String(this.userId);
        userObj.password = new String(this.password);
        userObj.authType = new String(this.authType);
        userObj.firstName = new String(this.firstName);
        userObj.lastName = new String(this.lastName);
        userObj.preferName = new String(this.preferName);
        userObj.gender =new String(this.gender);
        userObj.birthdayYear = new String(this.birthdayYear);
        userObj.birthdayMonth = new String(this.birthdayMonth);
        userObj.birthdayDay = new String(this.birthdayDay);
        userObj.birthday = new String(this.birthday);
        userObj.address = new String(this.address);
        userObj.city = new String(this.city);
        userObj.state = new String(this.state);
        userObj.preferPets = new String(this.preferPets);
        userObj.feedingPets = new String(this.feedingPets);
        userObj.petName = new String(this.petName);
        userObj.career =new String(this.career);
        userObj.createdDate = new String(this.createdDate);
        userObj.deletedDate =new String(this.deletedDate);
		
		return userObj;
	}
}
