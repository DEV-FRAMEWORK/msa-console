package com.msa.console.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.msa.console.dao.CustomerDao;
import com.msa.console.model.CustomerModel;

public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public void insCustomer(CustomerModel customerModel) {
		sqlSessionTemplate.insert("com.msa.console.dao.CustomerDao.insCustomer", customerModel);
		
	}

}

