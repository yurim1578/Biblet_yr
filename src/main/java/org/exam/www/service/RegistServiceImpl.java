package org.exam.www.service;

import java.util.HashMap;

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
		System.out.println();
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
}
