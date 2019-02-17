package com.msa.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.msa.console.dao.CodeDao;
import com.msa.console.dao.UtilDao;
import com.msa.console.model.CodeModel;
import com.msa.console.model.CompetitionModel;
import com.msa.console.model.ContractExtModel;

public class UtilDaoImpl implements UtilDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<CodeModel> selCode(String cdGroupId) {
		return sqlSessionTemplate.selectList("com.msa.console.dao.CodeDao.selCode", cdGroupId);
	}

	@Override
	public int selTenantIdSeq() {
		return sqlSessionTemplate.selectOne("com.msa.console.dao.UtilDao.selTenantIdSeq");
	}

	@Override
	public List<ContractExtModel> searchContract(String searchType, String searchValue) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchValue", searchValue);
		
		return sqlSessionTemplate.selectList("com.msa.console.dao.UtilDao.searchContract", map);
	}

	@Override
	public List<CompetitionModel> searchCompetition(String searchType, String searchValue) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchType", searchType);
		map.put("searchValue", searchValue);

		return sqlSessionTemplate.selectList("com.msa.console.dao.UtilDao.searchCompetition", map);
	}

	@Override
	public List<HashMap<String, String>> selListCompetitionImageMaxSeq(HashMap<String, String> map) {
		return sqlSessionTemplate.selectList("com.msa.console.dao.UtilDao.selListCompetitionImageMaxSeq", map);
	}
	
	
}

