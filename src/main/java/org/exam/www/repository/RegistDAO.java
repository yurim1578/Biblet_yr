package org.exam.www.repository;

import java.util.HashMap;

import org.exam.www.model.MemberVO;

public interface RegistDAO {
	public void memRegist(MemberVO member);
	public void updateKey(HashMap<String,String> map);
	public void updateStatus(MemberVO member);
}
