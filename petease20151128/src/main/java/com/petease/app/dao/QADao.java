package com.petease.app.dao;

import java.util.List;
import com.petease.app.domain.QA;

public interface QADao {
	public void insertQuestion(QA qa);
	public void updateAnswer(QA qa);
	//public List<QA> selectQATitleByPageNo(int pageNo);
	public QA selectQAById(long qaId);
}
