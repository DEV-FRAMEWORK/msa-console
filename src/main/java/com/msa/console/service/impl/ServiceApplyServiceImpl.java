package com.msa.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.console.dao.ServiceApplyDao;
import com.msa.console.model.ServiceDetailModel;
import com.msa.console.model.ServiceExtModel;
import com.msa.console.model.ServiceModel;
import com.msa.console.service.ServiceApplyService;

@Service
public class ServiceApplyServiceImpl implements ServiceApplyService{

	@Autowired
	private ServiceApplyDao serviceApplyDao;
	
	
	@Override
	public void insServiceApply(ServiceModel serviceModel) {
		serviceApplyDao.insServiceApply(serviceModel);
	}

	@Override
	public void insServiceApplyDetail(ServiceDetailModel serviceDetailModel) {
		serviceApplyDao.insServiceApplyDetail(serviceDetailModel);
	}

	@Override
	public List<ServiceExtModel> selListServiceApply(ServiceModel serviceModel) {
		List<ServiceExtModel> selListServiceApply = serviceApplyDao.selListServiceApply(serviceModel);
		
		return selListServiceApply;
	}

	@Override
	public List<ServiceExtModel> selServiceApply(ServiceModel serviceModel) {
		List<ServiceExtModel> selServiceApply = serviceApplyDao.selServiceApply(serviceModel);
		return selServiceApply;
	}

	@Override
	public List<ServiceDetailModel> selServiceApplyDetail(ServiceModel serviceModel) {
		List<ServiceDetailModel> selServiceApplyDetail = serviceApplyDao.selServiceApplyDetail(serviceModel);
		return selServiceApplyDetail;
	}

	@Override
	public void delServiceApply(ServiceModel serviceModel) {
		serviceApplyDao.delServiceApply(serviceModel);
	}

	@Override
	public void delServiceApplyDetail(ServiceDetailModel serviceDetailModel) {
		serviceApplyDao.delServiceApplyDetail(serviceDetailModel);
	}

}
