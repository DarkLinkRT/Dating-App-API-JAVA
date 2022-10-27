package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.model.User;

import java.util.List;

public interface MatchService {
    //Listar
    List<Match> getAllMatches(Long id);

    //Listar posibles matches
    List<Match> getAllPossibleMatches(Long id);
    //Guadar match
    void saveMatch(Match match);
}
