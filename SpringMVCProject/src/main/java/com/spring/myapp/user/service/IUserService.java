package com.spring.myapp.user.service;

import java.util.Date;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserService {
	void register(UserVO user) throws Exception;
	int isDuplicateId(String userId) throws Exception;
	UserVO login(LoginVO login) throws Exception;
	void KeepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	UserVO getUserWithSessionId(String sessionId) throws Exception;
}
