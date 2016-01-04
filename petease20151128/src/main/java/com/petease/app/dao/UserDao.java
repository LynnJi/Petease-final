package com.petease.app.dao;

import com.petease.app.domain.User;

public interface UserDao {
	public void insertUser(User user);
    public User selectUserById(String userId);
}
