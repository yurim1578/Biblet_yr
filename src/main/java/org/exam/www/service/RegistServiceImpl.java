package org.exam.www.service;

import java.util.HashMap;

import org.exam.www.exception.AlreadyExistEmailException;
import org.exam.www.exception.AlreadyExistIdException;
import org.exam.www.exception.SecurityCodeException;
import org.exam.www.model.AdministratorVO;
import org.exam.www.model.MemberVO;
import org.exam.www.repository.RegistDAO;
import org.springframework.stereotype.Service;

@Service
public class RegistServiceImpl implements RegistService{

	private RegistDAO registDAO;

	public void setRegistDAO(RegistDAO registDAO) {
		this.registDAO=registDAO;
	}
	
	@Override
	public void memRegist(MemberVO member) {
		
		
		String mem_email=member.getMem_email();
		int emailChkResult=registDAO.memEmailChk(mem_email);
		
		if(emailChkResult==1) {
			throw new AlreadyExistEmailException();
		}
		
		String mem_id=member.getMem_id();
		int idChkResult=registDAO.memIdChk(mem_id);
		
		if(idChkResult==1) {
			throw new AlreadyExistIdException();
		}
		
		registDAO.memRegist(member);
	}
	
	@Override
	public void updateKey(HashMap<String,String> map) {
		registDAO.updateKey(map);
	}
	
	@Override
	public void updateStatus(MemberVO member) {
		registDAO.updateStatus(member);
	}
	
	@Override
	public void admRegist(AdministratorVO admin) {
		String adm_email=admin.getAdm_email();
		int emailChkResult=registDAO.admEmailChk(adm_email);
		
		if(emailChkResult==1) {
			throw new AlreadyExistEmailException();
		}
		
		String adm_id=admin.getAdm_id();
		int idChkResult=registDAO.admIdChk(adm_id);
		
		if(idChkResult==1) {
			throw new AlreadyExistIdException();
		}
		
		if(!admin.getSecuritycode().equals("ABC")) {
			throw new SecurityCodeException();
		}		
		
		registDAO.admRegist(admin);
	};
	
	@Override
	public void updateAdmKey(HashMap<String,String> map) {
		registDAO.updateAdmKey(map);
	};
	
	@Override
	public void updateAdmStatus(AdministratorVO admin) {
		registDAO.updateAdmStatus(admin);
	};
}
