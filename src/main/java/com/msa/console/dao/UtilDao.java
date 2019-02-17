package com.msa.console.dao;

import java.util.List;

import com.msa.console.model.CodeModel;
import com.msa.console.model.CompetitionModel;
import com.msa.console.model.ContractExtModel;

import java.util.HashMap;

public interface UtilDao {
	public List<CodeModel> selCode(String cdGroupId);
	
	public int selTenantIdSeq();
	
	public List<ContractExtModel> searchContract(String searchType, String searchValue);
	
	public List<CompetitionModel> searchCompetition(String searchType, String searchValue);
	
	public List<HashMap<String, String>> selListCompetitionImageMaxSeq(HashMap<String, String> map);
	
}

