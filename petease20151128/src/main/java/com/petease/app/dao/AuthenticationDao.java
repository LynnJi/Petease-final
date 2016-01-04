package com.petease.app.dao;

import com.petease.app.domain.Authentication;

public interface AuthenticationDao {
	public void insertToken(Authentication auth);
    public Authentication selectUserInfoByToken(String token);
    public void deleteToken(String token);
}
