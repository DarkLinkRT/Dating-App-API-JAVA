package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.Match;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MatchService {
    //Listar
    List<Match> searchPossibleMatchesByUser(Long id);

    //Listar posibles matches
    List<Match> getAllMatchesByUser(Long id);
    //Guadar match
    void saveMatch(Match match);
}
