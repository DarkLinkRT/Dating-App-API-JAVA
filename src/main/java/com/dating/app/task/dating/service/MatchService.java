package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/***
 *
 *  MATCH SERVICE INTERFACE
 *
 * **/
@Service
public interface MatchService {
    /**
     *
     * Funcion que busca posibles usuarios para hacer match
     *
     * */
    HashMap<String,Object> searchPossibleMatchesByUser(Long id);
    /***
     *
     * Funcion que obtiene todos los matches del usuario
     *
     * **/
    HashMap<String,Object> getAllMatchesByUser(Long id);
    /**
     *
     * Funcion para guardar un match
     *
     * **/
    HashMap<String,Object> saveMatch(Match match);
    /**
     *
     * Funcion para calcular la compatibilidad entre dos usuarios
     *
     * **/
    HashMap<String,Object> getCompatibilityMatch(User id, User idMatch);
}
