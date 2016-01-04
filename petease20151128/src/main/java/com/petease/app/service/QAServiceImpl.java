package com.petease.app.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petease.app.common.Formatter;
import com.petease.app.dao.AuthenticationDao;
import com.petease.app.dao.QADao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.QA;


@Service("QAServiceImpl")
@Transactional
public class QAServiceImpl implements QAService {
	//Dependency injection
	@Autowired
	@Qualifier("QADaoJdbcImpl")
	private QADao qaDao;
	@Autowired
	@Qualifier("AuthenticationDaoJdbcImpl")
	private AuthenticationDao authDao;
	
	//Setter
	public void setQaDao(QADao qaDao) {
		this.qaDao = qaDao;
	}
	
	public void writeQuestion(QA qa, String token) {
		Authentication auth = authDao.selectUserInfoByToken(token);
		qa.setAsker(auth.getUser());
		qaDao.insertQuestion(qa);
	}

	@Override
	public void writeAnswer(QA qa) {
		// TODO Auto-generated method stub
		qaDao.updateAnswer(qa);
	}
	
}