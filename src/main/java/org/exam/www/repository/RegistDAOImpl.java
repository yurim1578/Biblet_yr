package org.exam.www.repository;

import java.util.HashMap;

import org.exam.www.model.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistDAOImpl implements RegistDAO{
	private SqlSessionTemplate sqlSessionTemplate;
	
	public RegistDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate=sqlSessionTemplate;
	}
	
	@Override
	public void memRegist(MemberVO member) {
		sqlSessionTemplate.insert("memRegist",member);
	}
	
	@Override
	public void updateKey(HashMap<String,String> map) {
		sqlSessionTemplate.update("updateKey",map);
	}
	
	@Override
	public void updateStatus(MemberVO member) {
		sqlSessionTemplate.update("updateStatus",member);
	}
}
