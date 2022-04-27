package org.exam.www.service;

import org.exam.www.model.CommandAuthInfo;

public interface LoginService {
	public CommandAuthInfo authenticate(String mem_id, String mem_pass) throws Exception;
}
