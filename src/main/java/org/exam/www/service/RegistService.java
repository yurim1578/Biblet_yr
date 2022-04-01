package org.exam.www.service;

import java.util.HashMap;

import org.exam.www.model.MemberVO;

public interface RegistService {
	
	public void memRegist(MemberVO member);
	public void updateKey(HashMap<String,String> map);
	public void updateStatus(MemberVO member);
}
