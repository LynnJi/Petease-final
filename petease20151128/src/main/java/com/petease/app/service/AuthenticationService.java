package com.petease.app.service;

import java.util.List;

import com.petease.app.dao.QADao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;

public interface AuthenticationService {
	public void writeUserInfo(User user);
	public User readUserInfoById(String userId);
	public Authentication writeToken(String userId, String password);
	public Authentication readUserInfoByToken(String token);
	public void removeToken(String token);
}
