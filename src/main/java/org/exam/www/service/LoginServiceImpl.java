package org.exam.www.service;

import org.exam.www.exception.IdPasswordNotMatchingException;
import org.exam.www.model.CommandAuthInfo;
import org.exam.www.model.MemberVO;
import org.exam.www.repository.LoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDAO loginDAO;

	@Override
	public CommandAuthInfo authenticate(String mem_id, String mem_pass) throws Exception {
		MemberVO member = loginDAO.selectById(mem_id);
        if(member == null) {
            throw new IdPasswordNotMatchingException();
        }
        if(!member.getMem_pass().equals(mem_pass)) {
            throw new IdPasswordNotMatchingException();
        }
        return new CommandAuthInfo(member.getMem_num(), member.getMem_id(), member.getMem_name());
    }
}
