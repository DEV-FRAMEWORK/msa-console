package com.msa.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.console.dao.ContractDao;
import com.msa.console.dao.CustomerDao;
import com.msa.console.model.ContractExtModel;
import com.msa.console.model.ContractModel;
import com.msa.console.model.CustomerModel;
import com.msa.console.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ContractDao contractDao;
	
	
	public List<ContractExtModel> selListContract(ContractExtModel contractExtModel){
		return contractDao.selListContract(contractExtModel); 
	};
	
	public List<ContractExtModel> selListContractCnt(ContractExtModel contractExtModel){
		return contractDao.selListContractCnt(contractExtModel); 
	};
	
	public List<ContractExtModel> selListCustCnt(ContractExtModel contractExtModel){
		return contractDao.selListCustCnt(contractExtModel); 
	};
	
	public List<ContractExtModel> selListCust(ContractExtModel contractExtModel){
		return contractDao.selListCust(contractExtModel); 
	};
	
	public ContractExtModel selContract(ContractExtModel contractExtModel){
		return contractDao.selContract(contractExtModel); 
	};
	
	@Override
	public void insCustomer(CustomerModel customerModel) {
		customerDao.insCustomer(customerModel);
		
	}

	@Override
	public void insContract(ContractModel contractModel) {
		contractDao.insContract(contractModel);
	}
	
	@Override
	public void delCust(ContractExtModel contractExtModel) {
		contractDao.delCust(contractExtModel);
	}
	
	@Override
	public void delContract(ContractExtModel contractExtModel) {
		contractDao.delContract(contractExtModel);
	}
	
	@Override
	public void upCust(ContractExtModel contractExtModel) {
		contractDao.upCust(contractExtModel);
	}
	
	@Override
	public void upContract(ContractExtModel contractExtModel) {
		contractDao.upContract(contractExtModel);
	}
}
