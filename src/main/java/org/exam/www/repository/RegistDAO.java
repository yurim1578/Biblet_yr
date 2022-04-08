package org.exam.www.repository;

import java.util.HashMap;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.MemberVO;

public interface RegistDAO {
	public void memRegist(MemberVO member);
	public void updateKey(HashMap<String,String> map);
	public void updateStatus(MemberVO member);
	public int memEmailChk(String mem_email);
	public int memIdChk(String mem_id);
	
	public void admRegist(AdministratorVO admin);
	public void updateAdmKey(HashMap<String,String> map);
	public void updateAdmStatus(AdministratorVO admin);
	public int admEmailChk(String adm_email);
	public int admIdChk(String adm_id);
}
