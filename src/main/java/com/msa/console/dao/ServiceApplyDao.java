package com.msa.console.dao;

import java.util.List;

import com.msa.console.model.ServiceDetailModel;
import com.msa.console.model.ServiceExtModel;
import com.msa.console.model.ServiceModel;

public interface ServiceApplyDao {
	public void insServiceApply(ServiceModel serviceModel);
	
	public void insServiceApplyDetail(ServiceDetailModel serviceDetailModel);
	
	public List<ServiceExtModel> selListServiceApply(ServiceModel serviceModel);
	
	public List<ServiceExtModel> selServiceApply(ServiceModel serviceModel);
	
	public List<ServiceDetailModel> selServiceApplyDetail(ServiceModel serviceModel);

	public void delServiceApply(ServiceModel serviceModel);
	
	public void delServiceApplyDetail(ServiceDetailModel serviceDetailModel);
}
