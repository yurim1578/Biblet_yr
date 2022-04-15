package org.exam.www.service;

import java.util.HashMap;
import java.util.List;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.CommandListAppr;
import org.exam.www.model.MemberVO;

public interface AdmPageService {
	public List<MemberVO> listOfMember();
	public int countMember();
	public int delete(HashMap<String,Object> map);
	public List<MemberVO> searchMember(MemberVO member);
	
	public List<AdministratorVO> listOfAdmin();
	public int countAdmin();
	public List<AdministratorVO> searchAdmin(AdministratorVO admin);
	
	public List<CommandListAppr> listOfAppraisal();
	public int countStar();
	public int countComment();
	public List<CommandListAppr> searchComments(CommandListAppr loa);
}
