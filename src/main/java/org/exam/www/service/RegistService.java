package org.exam.www.service;

import java.util.HashMap;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.MemberVO;

public interface RegistService {
	
	public void memRegist(MemberVO member);
	public void updateKey(HashMap<String,String> map);
	public void updateStatus(MemberVO member);
	
	public void admRegist(AdministratorVO admin);
	public void updateAdmKey(HashMap<String,String> map);
	public void updateAdmStatus(AdministratorVO admin);
}
