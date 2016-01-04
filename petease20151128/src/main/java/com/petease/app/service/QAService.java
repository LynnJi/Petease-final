package com.petease.app.service;

import java.util.List;

import com.petease.app.dao.QADao;
import com.petease.app.domain.QA;

public interface QAService {
	public void writeQuestion(QA qa, String token);
	public void writeAnswer(QA qa);
	//public QA readQAById(long qaId);
	//public List<QA> readQATitle();
}
