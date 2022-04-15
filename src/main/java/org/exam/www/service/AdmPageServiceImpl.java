package org.exam.www.service;

import java.util.HashMap;
import java.util.List;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.CommandListAppr;
import org.exam.www.model.MemberVO;
import org.exam.www.repository.AdministratorpageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdmPageServiceImpl implements AdmPageService{
	@Autowired
	private AdministratorpageDAO administratorpageDAO;

	public void setAdministratorpageDAO(AdministratorpageDAO administratorpageDAO) {
		this.administratorpageDAO=administratorpageDAO;
	}
	
	@Override
	public List<MemberVO> listOfMember() {
		return administratorpageDAO.listOfMember();
	};
	
	@Override
	public int countMember() {
		return administratorpageDAO.countMember();
	}
	
	@Override
	public int delete(HashMap<String,Object> map) {
		int result=administratorpageDAO.deleteMember(map);
		return result;
	};
	
	@Override
	public List<MemberVO> searchMember(MemberVO member){
		return administratorpageDAO.searchMember(member);
	};
	
	@Override
	public List<AdministratorVO> listOfAdmin(){
		return administratorpageDAO.listOfAdmin();
	};
	
	@Override
	public int countAdmin() {
		return administratorpageDAO.countAdmin();
	};
	
	@Override
	public List<AdministratorVO> searchAdmin(AdministratorVO admin){
		return administratorpageDAO.searchAdmin(admin);
	};
	
	@Override
	public List<CommandListAppr> listOfAppraisal(){
		return administratorpageDAO.listOfAppraisal();
	};
	
	@Override
	public int countStar() {
		return administratorpageDAO.countStar();
	}
	
	@Override
	public int countComment() {
		return administratorpageDAO.countComment();
	}
	
	@Override
	public List<CommandListAppr> searchComments(CommandListAppr loa){
		return administratorpageDAO.searchComments(loa);
	};
}
