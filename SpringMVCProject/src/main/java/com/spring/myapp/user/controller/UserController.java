package com.spring.myapp.user.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService service;
	
	//회원가입 처리요청
	//@RequestMapping(value="", method=RequestMethod.POST)
	@PostMapping("")
	public String register(@RequestBody UserVO user) throws Exception{
		logger.info("/replies: POST 요청 발생!");
		service.register(user);
		logger.info(user.toString()+" 회원가입 성공!");
		return "joinSuccess";
	}
	//회원가입 페이지 열기 요청
	@GetMapping("/join")
	public ModelAndView register() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/join");
		return mv;
	}
	//아이디 중복체크
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userId) throws Exception{
		int n = service.isDuplicateId(userId);
		System.out.println(n);
		if(n == 0 ) {
			System.out.println("아이디 사용 가능!");
			return "OK";
		}else{
			System.out.println("아이디 중복!!");
			return "FALSE";
		}
	}
	@GetMapping("/login")
	public ModelAndView login() throws Exception{
		logger.info("/user/login 요청!: GET");
		return new ModelAndView("/user/login");
	}
	
	//로그인 검증요청
	@PostMapping("/loginCheck")
	public String login(@RequestBody LoginVO login, HttpSession session
			, HttpServletResponse res) throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//# Session객체를 얻는 방법
		//1. HttpServletRequest 객체를 사용
		// request.getSession();
		//2. HttpSession 객체 사용
		/*
		# 클라이언트가 전송한 id값과 pw값을 가지고 DB에서 회원의 정보를 조회해서 불러온 다음 값 비교를 통해
		1. 아이디가 없을 경우 클라이언트 측으로 문자열 "idFail" 전송
		2. 비밀번호가 틀렸을 경우 문자열 "pwFail"전송
		3. 로그인 성공시 문자열 "loginSuccess"전송 
		*/
		String logId = login.getUserId();
		String logPw = login.getUserPw();
		boolean autoLogin = login.isAutoLogin();
		
		System.out.println("loginId/Pw: "+logId+", "+logPw);
		System.out.println("AutoLogin: "+autoLogin);
		
		UserVO user = service.login(login);
		if(user != null) {
			String uPw = user.getUserPw();
			System.out.println("userPw: "+uPw);
			if(encoder.matches(logPw, uPw) || logPw.equals(uPw)) {
				System.out.println("로그인 성공");
				session.setAttribute("login", user);
				//session.setMaxInactiveInterval(60*60); //세션 만료시간을 증가 (60* 60므로 1시간증가)
				//자동 로그인 쿠키 생성
				if(login.isAutoLogin()) {
					logger.info("자동 로그인 체크함!");
					long limitTime = 60*60*24*90;
					Date sessionLimit = new Date(System.currentTimeMillis() + (limitTime*1000));
					//쿠키 생성
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					//쿠키 속성 설정(수명)
					loginCookie.setMaxAge((int)limitTime);
					loginCookie.setPath("/");
					//생성된 쿠키를 response 객체에 실어서 전송.
					res.addCookie(loginCookie);
					service.KeepLogin(user.getUserId(), session.getId(), sessionLimit);
				}
				return "loginSuccess";
			}else {
				System.out.println("비밀번호 틀림");
				return "pwFail";
			}
		}else {
			System.out.println("아이디 틀림");
			return "idFail";
		}
	}
	//로그아웃 요청
	   @GetMapping("/logout")
	   public ModelAndView logout(HttpServletRequest request,
	            HttpServletResponse response,
	            HttpSession session) throws Exception {
	      
	      Object object = session.getAttribute("login");
	        if (object != null) {
	            UserVO userVO = (UserVO) object;
	            session.removeAttribute("login");
	            session.invalidate();
	            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
	            if (loginCookie != null) {
	                loginCookie.setPath("/");
	                loginCookie.setMaxAge(0);
	                response.addCookie(loginCookie);
	                service.KeepLogin(userVO.getUserId(), "none", new Date());
	            }
	        }
	      
	      return new ModelAndView("redirect:/");
	   }
	
	
}
