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
import com.petease.app.domain.Post;
import com.petease.app.domain.PostStatus;
import com.petease.app.domain.QA;
import com.petease.app.domain.User;
import com.petease.app.service.PostService;
import com.petease.app.service.QAService;




@Controller
public class PostController {
	@Autowired
	@Qualifier("PostServiceImpl")
	PostService postServ;
	
	@RequestMapping(value = "/postarticle", method=RequestMethod.GET)
	public ModelAndView getEmptyPost()
	{
		ModelAndView modelView;
		Post post;
		
		post = new Post();
		
		modelView = new ModelAndView("bbspost");
		modelView.addObject("post", post);
		return modelView;
	}	
	
	@RequestMapping(value = "/listarticle", method=RequestMethod.GET)
	public  @ResponseBody String listPost(int pageNo)
	{
		String responseHtml = "";
		List<Post> postList;
		postList = postServ.readArticleList(pageNo);
		if(postList.size() == 0) {
			responseHtml += "<span style='color:red'>You are in the end of this list, please go previous.</span>";
		}else{
			responseHtml += "<ul>";
			for(int i=0;i<postList.size();i++) {
				System.out.println(postList.get(i).toString());
				responseHtml += "<li style='padding:4px;0;'><a href='./viewpost?postId=" + postList.get(i).getPostId() + "'>" + postList.get(i).getTopic() + "</a>&nbsp;&nbsp;&nbsp;&nbsp;Posted by&nbsp;&nbsp;<span style='color:blue'>" + postList.get(i).getPoster().getPreferName() + "</span></li>";
			}
			responseHtml += "</ul>";
		}	
		return responseHtml;
	}

	@RequestMapping(value="/articlepost", method=RequestMethod.POST)
	public ModelAndView addArticle(@RequestParam("topic") String topic,
			                       @RequestParam("content") String content,
			                       @RequestParam("picUrl") MultipartFile file,
			                       HttpSession session,
			                       @CookieValue(value = "token", defaultValue = "logout") String token
			                       )
	{
		ModelAndView modelView;
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		//upload file
		Post post = new Post();
		post.setTopic(topic);
		post.setContent(content);
		if(token.equals("logout")) {
			System.out.println("Token expired");
			modelView = new ModelAndView("tokenfailed");
	 		return modelView;
		}else{
			if(!file.isEmpty())
			{
				if(file.getSize() > 3*1024*1024) {
		        	System.out.println("Size is overload");
					modelView = new ModelAndView("bbspostresponse");
			 		return modelView;
				}else if(!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png") && !file.getContentType().equals("image/gif")){
		        	System.out.println("Type is invalid");
					modelView = new ModelAndView("bbspostresponse");
			 		return modelView;
				}else{
		            try {
		        		String fileDest = "C:/Users/Ling/Desktop/In/project/userimage" + Formatter.datetime2String(new Date()) + token + file.getOriginalFilename();
		        		post.setPicUrl("/image/" + Formatter.datetime2String(new Date()) + token + file.getOriginalFilename());
		        		System.out.println(post.toString());
		            	if(!file.isEmpty()){
		                    byte[] bytes = file.getBytes();
		                    BufferedOutputStream stream =
		                            new BufferedOutputStream(new FileOutputStream(new File(fileDest)));
		                    stream.write(bytes);
		                    stream.close();
		            	}
		            } catch (Exception e) {
		            	//ex
		            	System.out.println("file upload exeption: "+e.getMessage());
						modelView = new ModelAndView("bbspostresponse");
				 		return modelView;
		            }
				}
			}
		}
    	postServ.writeArticle(post, token);
 		modelView = new ModelAndView("redirect:/");
 		session.setAttribute("post", post);
 		return modelView;
	}
	
	@RequestMapping(value = "/viewpost", method=RequestMethod.GET)
	public ModelAndView moveToArticleView(long postId)
	{
		ModelAndView modelView;
		Post post;
		post = postServ.readArticleByPostId(postId);
		modelView = new ModelAndView("viewbbs");
		modelView.addObject("postId", postId);
		modelView.addObject("post", post);
		return modelView;
	}	
	
	@RequestMapping(value = "/getcomment", method=RequestMethod.GET)
	public @ResponseBody String listComment(int pageNo, long postId)
	{
		String responseHtml = "";
		List<Comment> commentList = postServ.readCommentList(pageNo, postId);
		if(commentList.size() == 0) {
			responseHtml += "<div>[No comment is visable.]</div>";
		}else{
			for(int i=0;i<commentList.size();i++) {
				responseHtml += "<div>[" + commentList.get(i).getCommenter().getPreferName() + "](" + commentList.get(i).getCreateDate() + ")&nbsp;" + commentList.get(i).getComment() + "</div>";
			}
		}
		return responseHtml;
	}	
	
	@RequestMapping(value = "/getstatus", method=RequestMethod.GET)
	public @ResponseBody String getStatus(long postId)
	{
		String responseHtml = "";
		PostStatus postStatus = postServ.readPostStatus(postId);
		responseHtml += "{rate:'" + postStatus.getRate() + "',viewCount:'" + postStatus.getViewCount() + "'}";
		return responseHtml;
	}	
	
	@RequestMapping(value="/addcommentrate", method=RequestMethod.POST)
	public ModelAndView addCommentRate(@RequestParam("comment") String comment,
			                       @RequestParam("rate") String rate,
			                       @RequestParam("postId") String postId,
			                       HttpSession session,
			                       @CookieValue(value = "token", defaultValue = "logout") String token
			                       )
	{
		ModelAndView modelView;
		Interaction interaction;
		System.out.println("token:"+token);
		if(token.equals("logout")) {
			System.out.println("Token expired");
			modelView = new ModelAndView("tokenfailed");
		}else{
			interaction = new Interaction();
			interaction.getComment().setComment(comment);
			interaction.getComment().getArticle().setPostId(Integer.parseInt(postId));
			interaction.getRate().setRate(Integer.parseInt(rate));
			interaction.getRate().getArticle().setPostId(Integer.parseInt(postId));
	    	postServ.writeCommentRate(interaction, token);
	 		modelView = new ModelAndView("redirect:/");
	 		session.setAttribute("interaction", interaction);
		}
 		return modelView;
	}
	
}
