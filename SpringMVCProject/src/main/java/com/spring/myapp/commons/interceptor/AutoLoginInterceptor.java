package com.spring.myapp.commons.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.service.IUserService;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private IUserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		System.out.println("자동 로그인 인터셉터 발동!");
		//로컬 저장소에 저장된 쿠키 불러오기
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			System.out.println("로컬에서 로그인 쿠키 발견 됨");
			//로그인 쿠키에서 세션아이디 추출
			String sessionId = loginCookie.getValue();
			UserVO user = service.getUserWithSessionId(sessionId);
			if(user != null) {
				session.setAttribute("login",user);
			}
		}
		return true;
	}

}
