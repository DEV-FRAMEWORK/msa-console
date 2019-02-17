package com.msa.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.console.dao.MonitorDao;
import com.msa.console.model.MonitorModel;
import com.msa.console.service.MonitorService;

@Service 
public class MonitorServiceImpl implements MonitorService {
	
	@Autowired
	private MonitorDao monitorDao;
	
	public MonitorModel selMonitor(MonitorModel monitorModel){
		return monitorDao.selMonitor(monitorModel); 
	};
	
	public List<MonitorModel> selListMonitor(MonitorModel monitorModel){
		return monitorDao.selListMonitor(monitorModel); 
	};
	
	@Override
	public void insMonitor(MonitorModel monitorModel) {
		monitorDao.insMonitor(monitorModel);
	}
	
	@Override
	public void delMonitor(MonitorModel monitorModel) {
		monitorDao.delMonitor(monitorModel);
	}
	
	@Override
	public void upMonitor(MonitorModel monitorModel) {
		monitorDao.upMonitor(monitorModel);
	}
	
}
