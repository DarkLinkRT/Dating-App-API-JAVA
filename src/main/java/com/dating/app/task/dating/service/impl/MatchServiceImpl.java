package com.dating.app.task.dating.service.impl;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.repository.MatchRepository;
import com.dating.app.task.dating.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    //Listar
    @Override
    public List<Match> searchPossibleMatchesByUser(Long id){ return (List<Match>) matchRepository.searchMatchesByUser( id );}
    //Listar posibles matches
    @Override
    public List<Match> getAllPossibleMatches(Long id){ return (List<Match>) matchRepository.searchMatchesByUser( id );}
    //Guardar match
    public void saveMatch(Match match){
        if(match.getUserOne() == null){
            new NoSuchElementException("El usuario no debe ir vacio");
        }
        if(match.getUserTwo() == null){
            new NoSuchElementException("El usuario dos no debe ir vacio");
        }
        match.setCreatedAt( new Date() );
        matchRepository.save(match);
    }
}
