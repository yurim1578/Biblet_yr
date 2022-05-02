package org.exam.www.service;

import org.exam.www.exception.AuthstatusException;
import org.exam.www.exception.IdPasswordNotMatchingException;
import org.exam.www.model.AdministratorVO;
import org.exam.www.model.CommandAdminLogin;
import org.exam.www.model.CommandLogin;
import org.exam.www.model.MemberVO;
import org.exam.www.repository.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public CommandLogin authenticate(String mem_id, String mem_pass, int authstatus) throws Exception {
		MemberVO member = loginDAO.selectById(mem_id);
		if (member == null) {
			throw new IdPasswordNotMatchingException();
		}
		if (!member.getMem_pass().equals(mem_pass)) {
			throw new IdPasswordNotMatchingException();
		}
		if (member.getAuthstatus() == 0) {
			throw new AuthstatusException();
		}
		return new CommandLogin();
	}

	// 관리자 로그인
	@Override
	public CommandAdminLogin adminauthenticate(String adm_id, String adm_pass, int adm_authstatus) throws Exception {
		AdministratorVO admin = loginDAO.selectByAdminId(adm_id);
		if (admin == null) {
			throw new IdPasswordNotMatchingException();
		}
		if (!admin.getAdm_pass().equals(adm_pass)) {
			throw new IdPasswordNotMatchingException();
		}

		if (admin.getAdm_authstatus() == 0) {
			throw new AuthstatusException();
		}
		return new CommandAdminLogin();

	}
}
