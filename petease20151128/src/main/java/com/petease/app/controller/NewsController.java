package com.petease.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.petease.app.common.Formatter;
import com.petease.app.dao.PostDao;
import com.petease.app.domain.Comment;
import com.petease.app.domain.Interaction;
import com.petease.app.domain.News;
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;
import com.petease.app.service.NewsService;
import com.petease.app.service.PostService;
import com.petease.app.service.QAService;


@Controller
public class NewsController {
	@Autowired
	@Qualifier("NewsServiceImpl")
    NewsService newsServ;
	
	@RequestMapping(value = "/listnews", method=RequestMethod.GET)
	public  @ResponseBody String listNews()
	{
		String responseHtml = "";
		List<News> newsList;
		newsList = newsServ.readAllNews();
		if(newsList.size() == 0) {
			responseHtml += "<span>[There is no news.]</span>";
		}else{
			responseHtml += "<ul>";
			for(int i=0;i<newsList.size();i++) {
				System.out.println(newsList.get(i).toString());
				responseHtml += "<li><a href='./viewnews?newsId=" + newsList.get(i).getNewsId() + "'>" +newsList.get(i).getTopic() + "</span></li>";
			}
			responseHtml += "</ul>";
		}	
		return responseHtml;
	}
	
	@RequestMapping(value = "/viewnews", method=RequestMethod.GET)
	public ModelAndView moveToNewsView(long newsId)
	{
		ModelAndView modelView;
		News news;
		news = newsServ.readNewsByNewsId(newsId);
		modelView = new ModelAndView("newsview");
		modelView.addObject("news", news);
		return modelView;
	}	
	
}
