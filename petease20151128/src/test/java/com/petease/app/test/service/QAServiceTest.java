package com.petease.app.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petease.app.dao.QADao;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;
import com.petease.app.service.QAServiceImpl;

@ContextConfiguration("classpath:petease-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class QAServiceTest {
	QAServiceImpl qaServ = new QAServiceImpl();

	@Test
	public void case1TestWriteQuestion()
	{
		QA qa=new QA();
		StubQADao4Case1 stubQaDao = new StubQADao4Case1();
		
		qa.setTopic("cat");
		qa.setQuestion("question");
		qa.setAnswer("answer");
		
		User asker = new User();
		User admin = new User();
		asker.setUserId("abc@gmail.com");
		admin.setUserId("admin@petease.com");
		qa.setAsker(asker);
		qa.setAdmin(admin);
		
		qaServ.setQaDao(stubQaDao);
		qaServ.writeQuestion(qa,"token");	
	}
	class StubQADao4Case1 implements QADao {
		public void insertQuestion(QA qa) {
			User r_asker = new User();
			User r_admin = new User();
			r_asker.setUserId("abc@gmail.com");
			r_admin.setUserId("admin@petease.com");
			assertEquals(qa.getTopic(),"cat");
			assertEquals(qa.getQuestion(),"question");
			assertEquals(qa.getAnswer(),"answer");
			assertTrue(qa.getAsker().equals(r_asker));
			assertTrue(qa.getAdmin().equals(r_admin));
		}

		@Override
		public void updateAnswer(QA qa) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public QA selectQAById(long qaId) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
