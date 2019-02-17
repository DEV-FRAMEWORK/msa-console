package com.msa.console.dao;

import java.util.List;

import com.msa.console.model.CompetitionExtModel;
import com.msa.console.model.CompetitionImageModel;
import com.msa.console.model.CompetitionModel; 

public interface CompetitionDao {
	public void insCompetition(CompetitionModel competitionModel) ; 

	public void upCompetition(CompetitionModel competitionModel) ; 
	
	public List<CompetitionExtModel> selListCompetition(CompetitionModel competitionExtModel);
	
	public CompetitionExtModel selCompetition(CompetitionModel competitionExtModel);
	
	public void delCompetition(CompetitionModel competitionModel);
	
	public List<CompetitionImageModel> selListCompetitionImage(CompetitionImageModel competitionImageModel);
	
	public void insCompetitionImage(CompetitionImageModel competitionImageModel);
	
	public void delCompetitionImage(CompetitionImageModel competitionImageModel);
}

