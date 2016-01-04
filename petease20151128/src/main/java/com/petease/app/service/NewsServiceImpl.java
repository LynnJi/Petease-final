package com.petease.app.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petease.app.common.Formatter;
import com.petease.app.dao.AuthenticationDao;
import com.petease.app.dao.NewsDao;
import com.petease.app.dao.QADao;
import com.petease.app.domain.Authentication;
import com.petease.app.domain.News;
import com.petease.app.domain.QA;


@Service("NewsServiceImpl")
@Transactional
public class NewsServiceImpl implements NewsService {
	//Dependency injection
	@Autowired
	@Qualifier("NewsDaoJdbcImpl")
	private NewsDao newsDao;
	
	public List<News> readAllNews() {
		return newsDao.selectAllNews();
	}
	public News readNewsByNewsId(long newsId) {
		return newsDao.selectctNewsByNewsId(newsId);
	}
	
}