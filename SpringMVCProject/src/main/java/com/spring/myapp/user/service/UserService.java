package com.spring.myapp.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.repository.IUserDAO;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserDAO dao;
	
	@Override
	public void register(UserVO user) throws Exception {
		//회원 비밀번호를 인코딩
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("암호화 전: "+user.getUserPw());
		//비밀번호를 암호화하여 다시 user객체에 저장
		String securePw = encoder.encode(user.getUserPw());
		user.setUserPw(securePw);
		System.out.println("암호화 후: "+user.getUserPw());
		dao.register(user);
	}

	@Override
	public int isDuplicateId(String userId) throws Exception {
		return dao.isDuplicateId(userId);
	}

	@Override
	public UserVO login(LoginVO login) throws Exception {
		return dao.login(login);
	}

	@Override
	public void KeepLogin(String userId, String sessionId, Date sessionLimit) throws Exception {
		Map<String, Object> datas = new HashMap<>();
		datas.put("userId", userId);
		datas.put("sessionId", sessionId);
		datas.put("sessionLimit", sessionLimit);
		dao.KeepLogin(datas);
	}

	@Override
	public UserVO getUserWithSessionId(String sessionId) throws Exception {
		return dao.getUserWithSessionId(sessionId);
	}

}
