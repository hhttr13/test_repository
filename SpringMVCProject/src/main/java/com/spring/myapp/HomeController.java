package com.spring.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	//클라이언트 통신 테스트 페이지 열기
	@RequestMapping("/ajax/test")
	public String ajaxTest() {
		return "test/rest-api-view";
	}
	
	//댓글 CRUD 테스트 페이지 열기
	@RequestMapping("/ajax/crud")
	public String ajaxTest2() {
		return "test/reply-test";
	}
	//인터셉터 테스트 페이지 열기
	@GetMapping("/inter/test1")
	public String test1() throws Exception{
		return "test/interceptor-test";
	}
	@GetMapping("/inter/test2")
	public String test2(Model model) throws Exception{
		model.addAttribute("data", "HelloWorld!");
		return "test/interceptor-test";
	}
	
}




