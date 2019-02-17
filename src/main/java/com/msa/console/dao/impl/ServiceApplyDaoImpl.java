package com.msa.console.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.msa.console.dao.ServiceApplyDao;
import com.msa.console.model.ServiceDetailModel;
import com.msa.console.model.ServiceExtModel;
import com.msa.console.model.ServiceModel;

public class ServiceApplyDaoImpl implements ServiceApplyDao{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insServiceApply(ServiceModel serviceModel) {
		sqlSessionTemplate.insert("com.msa.console.dao.ServiceApplyDao.insServiceApply", serviceModel);
	}

	@Override
	public void insServiceApplyDetail(ServiceDetailModel serviceDetailModel) {
		sqlSessionTemplate.insert("com.msa.console.dao.ServiceApplyDao.insServiceApplyDetail",serviceDetailModel);
	}

	@Override
	public List<ServiceExtModel> selListServiceApply(ServiceModel serviceModel) {
		List<ServiceExtModel> selListServiceApply = 
				sqlSessionTemplate.selectList("com.msa.console.dao.ServiceApplyDao.selListServiceApply", serviceModel);
		return selListServiceApply;
	}

	@Override
	public List<ServiceExtModel> selServiceApply(ServiceModel serviceModel) {
		List<ServiceExtModel> selServiceApply =
				sqlSessionTemplate.selectList("com.msa.console.dao.ServiceApplyDao.selServiceApply", serviceModel);
		return selServiceApply;
	}

	@Override
	public List<ServiceDetailModel> selServiceApplyDetail(ServiceModel serviceModel) {
		List<ServiceDetailModel> selServiceApplyDetail =
				sqlSessionTemplate.selectList("com.msa.console.dao.ServiceApplyDao.selServiceApplyDetail", serviceModel);
		return selServiceApplyDetail;
	}

	@Override
	public void delServiceApply(ServiceModel serviceModel) {
		sqlSessionTemplate.delete("com.msa.console.dao.ServiceApplyDao.delServiceApply", serviceModel);
	}

	@Override
	public void delServiceApplyDetail(ServiceDetailModel serviceDetailModel) {
		sqlSessionTemplate.delete("com.msa.console.dao.ServiceApplyDao.delServiceApplyDetail", serviceDetailModel);
	}


}
