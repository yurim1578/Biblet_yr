package org.exam.www.service;

import org.exam.www.model.CommandAdminLogin;
import org.exam.www.model.CommandLogin;

public interface LoginService {
	public CommandLogin authenticate(String mem_id, String mem_pass, int authstatus) throws Exception;

	// 관리자 로그인
	public CommandAdminLogin adminauthenticate(String adm_id, String adm_pass, int adm_authstatus) throws Exception;

}
