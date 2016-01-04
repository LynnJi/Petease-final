package com.petease.app.service;

import java.util.List;

import com.petease.app.dao.QADao;
import com.petease.app.domain.News;
import com.petease.app.domain.QA;

public interface NewsService {
	public List<News> readAllNews();
	public News readNewsByNewsId(long newsId);
}
