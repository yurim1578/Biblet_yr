package org.exam.www.repository;

import org.exam.www.model.AdministratorVO;
import org.exam.www.model.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public MemberVO selectById(String mem_id) {
		return sqlSessionTemplate.selectOne("selectById", mem_id);
	}

	// 관리자 로그인
	@Override
	public AdministratorVO selectByAdminId(String adm_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectByAdminId", adm_id);
	}
}
