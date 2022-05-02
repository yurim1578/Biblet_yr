package org.exam.www.repository;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.MemberVO;

public interface LoginDAO {

	//로그인
	public MemberVO selectById(String mem_id);
	
	//관리자 로그인
	public AdministratorVO selectByAdminId(String adm_id);
}
