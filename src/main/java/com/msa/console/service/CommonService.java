package com.msa.console.service;

import java.util.HashMap;
import java.util.List;

import com.msa.console.model.CodeModel;
import com.msa.console.model.CompetitionModel;
import com.msa.console.model.ContractExtModel;

public interface CommonService {
    public List<CodeModel> selCode(String cdGroupId) ;
    
    public String selCodeByCdId(String cdGroupId, String cdId) ;
    
    public String selTenantIdSeq();
    
    public List<ContractExtModel> searchContract(String searchType, String searchValue);
    
    public List<CompetitionModel> searchCompetition(String searchType, String searchValue);
    
    public List<HashMap<String, String>> selListCompetitionImageMaxSeq(HashMap<String, String> map);
}
