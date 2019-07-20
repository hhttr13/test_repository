package com.spring.myapp.user;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.boardtest.BoardDAOTest;
import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.repository.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class UserTest {
	
	@Inject
	private IUserDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(IUserDAO.class);

/*	@Test
	public void insert() throws Exception {
		UserVO user = new UserVO();
		
		user.setUserId("qwerty");
		user.setUserPw("1q2w3e4r!");
		user.setUserName("홍길동");
		
		dao.register(user);
		logger.info("reg success");
	}*/
	
	//로그인 테스트
	@Test
	public void loginTest() throws Exception{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		LoginVO login = new LoginVO();
		login.setUserId("qwerty");
		login.setUserPw("1q2w3e4r!");
		
		UserVO user = dao.login(login);
		System.out.println("로그인 시도 회원정보: "+user);
		
		if(user != null) {
			String dbpw = user.getUserPw();
			System.out.println("DB password: "+dbpw);
			String inputpw = login.getUserPw();
			System.out.println("Input password: "+inputpw);
			
			if(encoder.matches(inputpw, dbpw) || dbpw.equals(inputpw)) {
				System.out.println("로그인 성공");
			}else {
				System.out.println("로그인 실패");
			}
		}else {
			System.out.println("존재하지 않는 회원입니다.");
		}
	}
	
	
	
}
