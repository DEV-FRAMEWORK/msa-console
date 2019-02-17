package com.msa.console.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.msa.console.dao.AdminDao;
import com.msa.console.model.AdminModel;
import com.msa.console.model.Member;

public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insertAdminUser(Member member) {
		sqlSessionTemplate.insert("com.msa.console.dao.AdminDao.insertAdminUser", member);
	}
	
	@Override
	public Member getUser(String username) {
		return sqlSessionTemplate.selectOne("com.msa.console.dao.AdminDao.getMember");
	}

	@Override
	public void createMember(Member member) {
		
	}

	@Override
	public Member getMemberById(String id) {
		return sqlSessionTemplate.selectOne("com.msa.console.dao.AdminDao.getMemberById", id);
	}

	@Override
	public List<Member> getMember() {
		return sqlSessionTemplate.selectList("com.msa.console.dao.AdminDao.getMember");
	}

	public List<AdminModel> selectListAdmin(AdminModel adminModel) {
		return sqlSessionTemplate.selectList("com.msa.console.dao.AdminDao.selectListAdmin", adminModel);
	}


}

