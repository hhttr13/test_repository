package com.spring.myapp.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterCeptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle() 호출됨!");
		return true;	//true: 인터셉터를 통과시킴!
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mv) throws Exception {
		System.out.println("postHandle() 호출됨!");
		Object data = mv.getModel().get("data");
		if(data!=null) {
			request.getSession().setAttribute("data", data);
			response.sendRedirect("/inter/test1");
		}
	}
	
}
