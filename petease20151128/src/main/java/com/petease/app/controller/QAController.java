package com.petease.app.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.petease.app.domain.QA;
import com.petease.app.domain.User;
import com.petease.app.service.QAService;




@Controller
public class QAController {
	@Autowired
	@Qualifier("QAServiceImpl")
	QAService qaServ;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "solution";
	}
	
	@RequestMapping(value = "/qa", method=RequestMethod.GET)
	public ModelAndView getEmptyQa()
	{
		ModelAndView modelView;
		QA qa;
		
		qa = new QA();
		
		modelView = new ModelAndView("question");
		modelView.addObject("qa", qa);
		return modelView;
	}

	@RequestMapping(value="/qa", method=RequestMethod.POST)
	public ModelAndView addQa(@ModelAttribute("qa") @Valid QA qa,@CookieValue(value = "token", defaultValue = "logout") String token, BindingResult result, HttpSession session)
	{
		ModelAndView modelView;
		System.out.println(result.toString());
		qaServ.writeQuestion(qa, token);
 		modelView = new ModelAndView("redirect:/");
 		session.setAttribute("qa", qa);
 		return modelView;
	}
	
	@RequestMapping(value="/updateqa", method=RequestMethod.POST)
	public ModelAndView updateQa(@ModelAttribute("updateqa") @Valid QA updateqa, BindingResult result, HttpSession session)
	{
		ModelAndView modelView;
		System.out.println(result.toString());
		qaServ.writeAnswer(updateqa);
		modelView = new ModelAndView("redirect:/");
 		//session.setAttribute("updateqa", qa);
 		return modelView;
	}
}
