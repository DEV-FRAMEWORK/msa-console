package com.msa.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msa.console.dao.CompetitionDao;
import com.msa.console.model.CompetitionExtModel;
import com.msa.console.model.CompetitionImageModel;
import com.msa.console.model.CompetitionModel;
import com.msa.console.service.CompetitionService;

@Service
public class CompetitionServiceImpl implements CompetitionService{ 
	
	@Autowired
	private CompetitionDao competitionDao;
	
	@Override
	public void insCompetition(CompetitionModel competitionModel) {
		competitionDao.insCompetition(competitionModel);
	}

	@Override
	public void upCompetition(CompetitionModel competitionModel) {
		competitionDao.upCompetition(competitionModel);
	}

	@Override
	public List<CompetitionExtModel> selListCompetition(CompetitionModel competitionModel) {
		return competitionDao.selListCompetition(competitionModel);
	}
	
	@Override
	public CompetitionExtModel selCompetition(CompetitionModel competitionModel) {
		return competitionDao.selCompetition(competitionModel);
	}

	@Override
	public void delCompetition(CompetitionModel competitionModel) {
		competitionDao.delCompetition(competitionModel);
	}

	@Override
	public List<CompetitionImageModel> selListCompetitionImage(CompetitionImageModel competitionImageModel) {
		return competitionDao.selListCompetitionImage(competitionImageModel);
	}

	@Override
	public void insCompetitionImage(CompetitionImageModel competitionImageModel) {
		competitionDao.insCompetitionImage(competitionImageModel);
	}

	@Override
	public void delCompetitionImage(CompetitionImageModel competitionImageModel) {
		competitionDao.delCompetitionImage(competitionImageModel);
	}
	
}
