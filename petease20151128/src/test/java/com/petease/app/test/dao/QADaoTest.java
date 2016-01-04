package com.petease.app.test.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petease.app.dao.QADao;
import com.petease.app.dao.jdbc.QARowMapper;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration("classpath:petease-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QADaoTest {
	@Autowired
	@Qualifier("QADaoJdbcImpl")
	private QADao qaDao;
	private QA qaLookup;
	
	@Autowired
	private DataSource dataSource;
	private NamedParameterJdbcTemplate dbTemplate;
	private SimpleJdbcInsert jdbcInsert;
	private QARowMapper qaRowMapper;
	
	@Before
	public void init()
	{
		qaLookup=new QA();
		qaLookup.setTopic("dog");
		qaLookup.setQuestion("question1");
		qaLookup.setAnswer("answer1");
		
		User asker = new User();
		User admin = new User();
		asker.setUserId("user@gmail.com");
		admin.setUserId("admin@petease.com");
		qaLookup.setAsker(asker);
		qaLookup.setAdmin(admin);
	}
	
	@Test
	public void testInsertQuestion()
	{
		QA qa=new QA();
		QA qaResult;

		qa=new QA();
		qa.setTopic("dog");
		qa.setQuestion("question1");
		qa.setAnswer("answer1");
		
		User asker = new User();
		User admin = new User();
		asker.setUserId("user@gmail.com");
		admin.setUserId("admin@petease.com");
		qa.setAsker(asker);
		qa.setAdmin(admin);
		
		qaDao.insertQuestion(qa);
		assertTrue(qa.getQaId()>0);
		
		qaResult = selectById4Test(qa.getQaId());
		
		assertEquals(qaResult.getAsker().getUserId(), qaLookup.getAsker().getUserId());
		assertEquals(qaResult.getTopic(), qaLookup.getTopic());
		assertEquals(qaResult.getQuestion(), qaLookup.getQuestion());
		assertEquals(qaResult.getAnswer(), qaLookup.getAnswer());
		assertEquals(qaResult.getAdmin().getUserId(), qaLookup.getAdmin().getUserId());
		
	}
	public QA selectById4Test(long id) {
		List<QA> qaList;
		String querySql="select * from qa where qa_id=:qaId";
		HashMap<String,Object> param=new HashMap<String,Object>();
		param.put("qaId", id);
		qaList=dbTemplate.query(querySql, param, qaRowMapper);
		if(qaList.size()!=1)return null;
		return qaList.get(0);
	}
}
