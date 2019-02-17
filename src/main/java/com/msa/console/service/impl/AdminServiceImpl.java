package com.msa.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.console.dao.AdminDao;
import com.msa.console.model.AdminModel;
import com.msa.console.service.AdminService; 


@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	
	public List<AdminModel> selectListAdmin(AdminModel adminModel){
		return adminDao.selectListAdmin(adminModel);
	};
	
}
