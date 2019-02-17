package com.msa.console.service;

import java.util.List;

import com.msa.console.model.MonitorModel;

public interface MonitorService {
    
	public MonitorModel selMonitor(MonitorModel monitorModel);
	
	public List<MonitorModel> selListMonitor(MonitorModel monitorModel);
	
    public void insMonitor(MonitorModel monitorModel);
    
    public void delMonitor(MonitorModel monitorModel);
    
    public void upMonitor(MonitorModel monitorModel);
    
}
