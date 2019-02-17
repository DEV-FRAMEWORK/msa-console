package com.msa.console.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.msa.console.dao.CompetitionDao;
import com.msa.console.model.CompetitionExtModel;
import com.msa.console.model.CompetitionImageModel;
import com.msa.console.model.CompetitionModel; 

public class CompetitionDaoImpl implements CompetitionDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insCompetition(CompetitionModel competitionModel) {
		sqlSessionTemplate.insert("com.msa.console.dao.CompetitionDao.insCompetition", competitionModel);
	}

	@Override
	public void upCompetition(CompetitionModel competitionModel) {
		sqlSessionTemplate.update("com.msa.console.dao.CompetitionDao.upCompetition", competitionModel);
	}

	@Override
	public List<CompetitionExtModel> selListCompetition(CompetitionModel competitionModel) {
		return sqlSessionTemplate.selectList("com.msa.console.dao.CompetitionDao.selListCompetition", competitionModel);
	}
	
	@Override
	public CompetitionExtModel selCompetition(CompetitionModel competitionModel) {
		return sqlSessionTemplate.selectOne("com.msa.console.dao.CompetitionDao.selCompetition", competitionModel);
	}

	@Override
	public void delCompetition(CompetitionModel competitionModel) {
		sqlSessionTemplate.delete("com.msa.console.dao.CompetitionDao.delCompetition", competitionModel);
	}

	@Override
	public List<CompetitionImageModel> selListCompetitionImage(CompetitionImageModel competitionImageModel) {
		return sqlSessionTemplate.selectList("com.msa.console.dao.CompetitionDao.selListCompetitionImage", competitionImageModel);
	}

	@Override
	public void insCompetitionImage(CompetitionImageModel competitionImageModel) {
		sqlSessionTemplate.insert("com.msa.console.dao.CompetitionDao.insCompetitionImage", competitionImageModel);
	}

	@Override
	public void delCompetitionImage(CompetitionImageModel competitionImageModel) {
		sqlSessionTemplate.delete("com.msa.console.dao.CompetitionDao.delCompetitionImage", competitionImageModel);
	}
	
}

