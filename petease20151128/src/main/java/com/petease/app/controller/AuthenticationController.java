package com.petease.app.controller;

import java.lang.reflect.Method;
import java.net.HttpCookie;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.petease.app.domain.Authentication;
import com.petease.app.domain.User;
import com.petease.app.service.AuthenticationService;
import com.sun.mail.smtp.SMTPSaslAuthenticator;

@Controller
public class AuthenticationController {
	@Autowired
	@Qualifier("AuthenticationServiceImpl")
	AuthenticationService authenticationServ;

	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public ModelAndView getEmptyUser()
	{
		ModelAndView modelView;
		User user;
		
		user = new User();
		
		modelView = new ModelAndView("signup");
		modelView.addObject("user", user);
		return modelView;
	}
	
	@RequestMapping(value = "/signon", method=RequestMethod.GET)
	public ModelAndView showSignOn()
	{
		ModelAndView modelView;
		User user;
		
		user = new User();
		
		modelView = new ModelAndView("login");
		modelView.addObject("user", user);
		return modelView;
	}
	
	@RequestMapping(value="/signon", method=RequestMethod.POST)
	public ModelAndView goSignOn(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session, HttpServletResponse response)
	{
		Authentication auth = new Authentication();
		System.out.println(user.toString());
		ModelAndView modelView;
		System.out.println(result.toString());
		auth = authenticationServ.writeToken(user.getUserId(), user.getPassword());
 		modelView = new ModelAndView("index");
 		session.setAttribute("user", user);
 		Cookie tokenCookie = new Cookie("token", auth.getToken());
 		response.addCookie(tokenCookie);
 		return modelView;
	}
	
	@RequestMapping(value = "/signoninfo", method=RequestMethod.GET)
	public @ResponseBody String getSignOnInfo(@CookieValue(value = "token", defaultValue = "logout") String tokenCookie)
	{
		Authentication auth;
		auth = authenticationServ.readUserInfoByToken(tokenCookie);
		return auth == null?"":auth.getUser().getPreferName();
	}	
	
	@RequestMapping(value = "/signoff", method=RequestMethod.GET)
	public ModelAndView signoff(@CookieValue(value = "token", defaultValue = "logout") String tokenCookie, HttpSession session, HttpServletResponse response)
	{
		ModelAndView modelView;
		authenticationServ.removeToken(tokenCookie);
		Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
 		modelView = new ModelAndView("redirect:/");
 		return modelView;
	}
	
	@RequestMapping(value = "/signupresponse", method=RequestMethod.GET)
	public ModelAndView getUserById(String userId)
	{
		ModelAndView modelView;
		User user;
		
		user=authenticationServ.readUserInfoById(userId);
		if(user==null)
		{
			modelView = new ModelAndView("noDataReturn");
			return modelView;
			
		}
		modelView = new ModelAndView("signupresponse");
		modelView.addObject("user", user);
		return modelView;
	}	
	
	@RequestMapping(value = "/useridverification", method=RequestMethod.GET)
	public @ResponseBody String getUserById4DuplicatedCheck(String userId)
	{
		User user;
		
		user=authenticationServ.readUserInfoById(userId);
		if(user==null)
		{
			return "false";
		}
		return "true";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session)
	{
		System.out.println(user.toString());
		ModelAndView modelView;
		System.out.println(result.toString());
		String password = user.getPassword();
		try{
			authenticationServ.writeUserInfo(user);
	 		modelView = new ModelAndView("redirect:/signupresponse?userId=" + user.getUserId());
	 		session.setAttribute("user", user);
	
		    // Recipient's email ID needs to be mentioned.
		    String to = user.getUserId();
	
		    // Sender's email ID needs to be mentioned
		    String from = "petease2015@gmail.com";
		    
		    // Sender's email password needs to be mentioned
		    String pwd = "petease12128";
	
		    // Assuming you are sending email from localhost
		    String host = "smtp.gmail.com";
		    
		    // Mail content
		    String htmlStr = "";
		    String mainLogo = "https://lh3.googleusercontent.com/6opxLPrscT1xJPfe99kH5gQvIBaox1r7OlWCg91-G24PnKpupQ7DdPtulBhX3UAjRctCW7Y7YlddjOmSqTFyjV8qQmQS0dcjdTcAcSHcDbnVg-0j-M1_v3NYOol5Ce6nUIb32RqM7IoLKY7Kea_RPj7fEtPNAG40RjYHp3Bo26A011wHKSw9m1w9S0ao44To8ku0O43U7pkYgtKZR2FeVh8B7d-rNyWak1WtTdinW0_3Vd2mCOmfkAROuBEhPakU7et67KkpQr4XL8mSjmQSZjBulwkHgX7g37b9JLAWNj71oelTwaANKS0oJ4nwqlIAaYQAD4jPclXW0eu9fy6zNYQalCJCdOB6GB5nLhTzIle7xcbG_ToG4cvnQ05uWPc92Wrz3sytaIRWFr5kaGU0JmGXgSDF6CZ_CimbxlGc2SarXQztQ0dH_hYI8EFZHpDl7OzrwvP1aY9hHnQZVy7tOJcbe9hUsDURDTp6PyMkSNciCoU-pxDRdDDDC1WaXK9cNyV-ZVPUYUvJqvJiWr9bJdIPsGkqMwj2DB5bI01yzw=w728-h330-no";
		    //String headerImg = "https://lh3.googleusercontent.com/a9ZSj0yYDUar7IOmAy5EL3GRA8NRssRR_6WgFSd6TLmQHJnlhzfx0VA_RceMkZvepxjhQ0xqP44H9Lc1UZcVFrOSNJAHchvRmnm_8r-HBNTAy8WdVWyUTJOA-CFUz87GPitTzCg7hKgFSkQn87NaRrJZOc4z_pgoLzImMK6FCj1AqvHs_AFFqExgFgjEcndV6zSlQUxjvo5Xp7zeUYd8U1DvDHjMoRb3RLFC0Xl26HUJzZabsNiEAjzgFT49_eJeDjGAvbwboKWsVaBdYTKghU0tsZJXEgsPqVrrfh-nwdkJ28cAUHUI6vOzbyBppkSjjr193HtcBnUjeQKPsh_tAQORDfF7GPi0fxsavJOuUfBvTUxoDAOaGjUEbb9-mUaRdfiksO-TLqdtaWFEtQPC72_RiJJBYoaFD5OFRyP1ems1apee4IMH_7bdIBlFF7eF3MF6bo1naGqASfZ0jDoFuK8tf51xm-3nTGXYSYykP71lUy-LxhSpFznqLDc0uphbqsKeslV12JSDLk-WKIY-nSpMpOrwSu7e3Ezug9mWGw=w752-h329-no";
		    //String footerLine = "https://lh3.googleusercontent.com/COne9jWhSRAZJXJFYtzc7gI9gzcskJpo8jXHXnnxUSIGkzda85px1bNiaWkT6FtSp19HgsWjA0NMnor10Zvtp20Cv89EXHBhpvi1BJJz6BGzo7JVIjqktXt2pUmBkU0hLypWAz_pBrqs4Cw1DnFRge6ezlRxJW69xMmdrzUyGdXN5uNEcROk0fEVf5xyVHwu9J_-MpvpKlpQoSoBLAw-LHrzA50N2myK6fiiRGMeM4DYj1ObnXpUofW4m2Ars50PbN7YbzaVWhf-ly2Tpx3LQs4NRvpHOB217WiFMPRzHuedJ5wGcIQj6qy4R_HCiY6j2kelIgW8IwC63tJ2xX24xnk2-Dl-egui13EaZqflnylawNZ8GAZHxaaXX7pIKz4oRmwEHZAT6vWtZviXG2wTkPHP74tXhkGCi6BWE4aAncKtiRFE9kQbLUi4yceOIIiyBRLG5fnXDoWnqMR8tclxpOr0pKmPpPc58H4cUCMt_PSCfRV6W4e1lFr1Y0YcQ6eY2vvCKzcQHZKkW46D5o-lv7LHCLu42S7NL8eKS0nVEA=w230-h30-no";
	        String contactMe = "https://lh3.googleusercontent.com/_Lxe8Laafbh-MUNPEeYBYKy3lVS85AkiKORC_qr5FNdT0CufSPe0hEyo_I4Lzc0LCgu4myfWPoKqgIAr8bUm_WgECFzWKQRWXKnaARWzleSF5fsz2Ur3G1D9L5cFsBkJ0JTiD8LVuYLzCEh6HnRiiXDTZXkMk64xI7AHg1a4s0vDRzBrMioc6mgqygzWzQpEWpQSNAiGH63edTn1FpAKYsi0hS94KP2bAiXYaiGDRH-AZfTloEVIKyRej0oF6cK2aBFNw2OuOZpdxCvvydVXpAP4v30eY8MuMlCELNh-JdpSe4J5iC_YN27muI5NlJYDb-S8AUalP6uf8dbVWwK6-Xs-QUfQ-lqzrLK4FccC9zSGuig5sU0diEQuUcHcf7IMaQYMf6bzmOAKjMD0iah4fSNKLX1dH-yDvOuP8ZRgYudUJyay1PeHVjhoz6tlueLpEsMje6bYBGxWf4n6qUX3ivBwD-WaihNBsbC5-umgcsk9VzuhDhAo068uGWxwwuXaoZDT06wgKIAB9v_2vMyIRLW1Z0urJN512Fi-lSvm6w=s500-no";
		    
	        htmlStr += "<html><head><meta charset='UTF-8'><style>.item{font-size: 20px;}.view_content{border: solid #eeeeee 1px;}.blank{border: solid #dddddd 3px;}.block{padding:10px 0;}</style></head>";
	        htmlStr += "<body><div><div><div style='position: relative;z-index: 2'>";
	        htmlStr += "<img id='main_logo' src='";
	        htmlStr += mainLogo; 
	        htmlStr += "' alt='pet ease' style='width: 250px;padding: 20px;position: absolute; top:50%'/>";
	        htmlStr += "</div><div style='margin-left: auto;margin-right: auto;text-align: center;z-index: 1'>";
	        htmlStr += "</div></div></div>";
	        htmlStr += "<div style='height:100%'><div style='font-size: 30px;color: brown'>Congratulations! ";
	        htmlStr += user.getLastName();
	        htmlStr += " ";
	        htmlStr += user.getFirstName();
	        htmlStr += "</div><div><div><div class='item'>User Name:</div><div class='view_content'>";
	        htmlStr += user.getUserId();
	        htmlStr += "</div></div>";
	        htmlStr += "<div class='block'><div class='col-sm-4 item'>Password:</div><div class='view_content'>";
	        htmlStr += password;
	        htmlStr	+= "</div></div>";
	        htmlStr += "<div class='block'><button style='width: 100%; height:40px; color: #245269;' onclick='window.location.href='/''>Back to homepage</button></div></div></div></div>";
	        htmlStr += "<div><div style='padding: 20px'></div><div style='padding: 20px;'><div style='text-align: center;color: #ff3333'>Copyright @ CS595E Group6</div><div style='text-align: right'>Contact me</div></div></div></body></html>";
	        
		    // Get system properties
		    Properties properties = System.getProperties();
	
		    // Setup mail server
		    properties.setProperty("mail.smtp.host", host);
	
		    // Get the default Session object.
		    Session mailSession = Session.getDefaultInstance(properties);
	
		    try{
		        // Create a default MimeMessage object.
		        MimeMessage message = new MimeMessage(mailSession);
	
		        // Set From: header field of the header.
		        message.setFrom(new InternetAddress(from));
	
		        // Set To: header field of the header.
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
		        // Set Subject: header field
		        message.setSubject("Pet Ease sign up confirmation for "+user.getFirstName());
	
		        // Now set the actual message
		        message.setContent(htmlStr,"text/html");
	
		        // Send message
		        Transport transport = mailSession.getTransport("smtps");
		        transport.connect (host, 465, from, pwd);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();  
		        System.out.println("Sent message successfully....");
		    }catch (Exception mex) {
		        mex.printStackTrace();
		    }
		}catch(DuplicateKeyException de) {
			modelView = new ModelAndView("duplicatedSignupReturn");
		}
 		return modelView;
	}
	

}
