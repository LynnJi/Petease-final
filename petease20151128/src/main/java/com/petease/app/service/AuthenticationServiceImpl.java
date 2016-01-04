package com.petease.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petease.app.common.Formatter;
import com.petease.app.dao.AuthenticationDao;
import com.petease.app.dao.QADao;
import com.petease.app.dao.UserDao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;


@Service("AuthenticationServiceImpl")
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
	//Dependency injection
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	private UserDao userDao;
	@Autowired
	@Qualifier("AuthenticationDaoJdbcImpl")
	private AuthenticationDao authDao;
	
	//Setter
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void writeUserInfo(User user) throws DuplicateKeyException{
		user.setBirthday(user.getBirthdayYear() + "-" + user.getBirthdayMonth() + "-" + user.getBirthdayDay());
		System.out.println("birthday="+user.getBirthday());
		System.out.println("password(Before encrypt)="+user.getPassword());
		user.setPassword(Formatter.getMD5(user.getPassword()));
		System.out.println("password(Afer encrypt)="+user.getPassword());
		userDao.insertUser(user);
	}
	
	public User readUserInfoById(String userId) {
		return userDao.selectUserById(userId);
	}
	
	public Authentication writeToken(String userId, String password) {
		User user = userDao.selectUserById(userId);
		Authentication auth = new Authentication();
		if(user !=  null) {
			if(Formatter.getMD5(password).equals(user.getPassword())) {
				auth.setUser(user);
				auth.setToken(Formatter.getMD5(user.getUserId() + Formatter.datetime2String(new Date())));
				System.out.println(auth.toString());
				authDao.insertToken(auth);
				return auth;
			}else{
				//password missed
				return auth;
			}
		}else{
			//user id missed
			return auth;
		}
	}
	
	public Authentication readUserInfoByToken(String token) {
		return authDao.selectUserInfoByToken(token);
	}
	
	public void removeToken(String token) {
		authDao.deleteToken(token);
	}
}