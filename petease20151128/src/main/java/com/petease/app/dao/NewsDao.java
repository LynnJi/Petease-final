package com.petease.app.dao;

import java.util.List;

import com.petease.app.domain.News;
import com.petease.app.domain.QA;

public interface NewsDao {
	public List<News> selectAllNews();
	public News selectctNewsByNewsId(long newsId);
}
