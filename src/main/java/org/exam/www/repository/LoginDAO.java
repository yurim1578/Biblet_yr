package org.exam.www.repository;

import org.exam.www.model.MemberVO;

public interface LoginDAO {

	//로그인
	public MemberVO selectById(String mem_id);
}
