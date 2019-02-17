package com.msa.console.service;

import java.util.List;

import com.msa.console.model.CompetitionExtModel;
import com.msa.console.model.CompetitionImageModel;
import com.msa.console.model.CompetitionModel;

public interface CompetitionService {

    public void insCompetition(CompetitionModel competitionModel) ;
    
    public void upCompetition(CompetitionModel competitionModel) ; 
    
    public List<CompetitionExtModel> selListCompetition(CompetitionModel competitionModel);
    
    public CompetitionExtModel selCompetition(CompetitionModel competitionModel);
    
    public void delCompetition(CompetitionModel competitionModel);
    
    public List<CompetitionImageModel> selListCompetitionImage(CompetitionImageModel competitionImageModel);
    
    public void insCompetitionImage(CompetitionImageModel competitionImageModel);
    
    public void delCompetitionImage(CompetitionImageModel competitionImageModel);
}
