package com.spring.myapp.user.repository;

import java.util.Map;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserDAO {
	//회원가입 처리
	void register(UserVO user) throws Exception;
	//아이디 중복확인 처리
	int isDuplicateId(String userId) throws Exception;
	//로그인 시도 회원정보 조회처리
	UserVO login(LoginVO login) throws Exception;
	//자동로그인 유지처리
	void KeepLogin(Map<String, Object> datas) throws Exception;
	//재방문시 자동로그인 처리
	UserVO getUserWithSessionId(String sessionId) throws Exception;
}
