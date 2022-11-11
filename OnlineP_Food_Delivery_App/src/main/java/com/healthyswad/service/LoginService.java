package com.healthyswad.service;

import com.healthyswad.dto.LoginDTO;
import com.healthyswad.exception.LoginException;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
