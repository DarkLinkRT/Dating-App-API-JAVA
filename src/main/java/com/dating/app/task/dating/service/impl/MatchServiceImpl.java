package com.dating.app.task.dating.service.impl;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.repository.MatchRepository;
import com.dating.app.task.dating.repository.UserRepository;
import com.dating.app.task.dating.service.MatchService;
import com.dating.app.task.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;
    private HashMap<String, Object> response = new HashMap<>();

    /**
     *
     * Funcion que busca posibles usuarios para hacer match
     *
     * */
    @Override
    public HashMap<String,Object> searchPossibleMatchesByUser(Long id){
        response.put("data", matchRepository.searchPossibleMatchesByUser(id));
        response.put("status", true);
        response.put("message", "");
        return response;
    }
    /***
     *
     * Funcion que obtiene todos los matches del usuario
     *
     * **/
    @Override
    public HashMap<String,Object> getAllMatchesByUser(Long id){
        response.put("data", matchRepository.getAllMatchesByUser(id));
        response.put("status", true);
        response.put("message", "");
        return response;
    }
    /**
     *
     * Funcion para guardar un match
     *
     * **/
    @Override
    public HashMap<String,Object> saveMatch(Match match){
        response.put("status", false);
        UserService userService = new UserServiceImpl();
        //Comienza validacion
        if(match.getUserId().equals(null) || match.getUserId().equals("")){
            response.put("message", "El ID del usuario uno no es valido");
            if(userService.isUserRegistered(match.getUserId())){
                response.put("message", "El usuario no existe en la BD");
            }
            return response;
        }
        if(match.getUserMatchId().equals(null) || match.getUserMatchId().equals("")){
            response.put("message", "El ID del usuario match uno no es valido");
            if(userService.isUserRegistered(match.getUserMatchId())){
                response.put("message", "El usuario match no existe en la BD");
            }
            return response;
        }
        //Si esta validado...
        match.setCreatedAt( new Date() );
        matchRepository.save(match);
        response.put("status", true);
        response.put("message", "Match guardado");
        return response;
    }
    /**
     *
     * Funcion para obtener la compatibilidad entre dos usuarios
     *
     * **/
    public HashMap<String,Object> getCompatibilityMatch(User user, User userMatch){
        response.put("status", false);
        UserService userService = new UserServiceImpl();
        //Comienza validacion
        if(user.getId().equals(null) || user.getId().equals("")){
            response.put("message", "El ID del usuario uno no es valido");
            if(userService.isUserRegistered(user)){
                response.put("message", "El usuario no existe en la BD");
            }
            return response;
        }
        if(userMatch.getId().equals(null) || userMatch.getId().equals("")){
            response.put("message", "El ID del usuario match uno no es valido");
            if(userService.isUserRegistered(userMatch)){
                response.put("message", "El usuario match no existe en la BD");
            }
            return response;
        }
        //Si esta validado...
        //Check Compatibility
        response.put("status",true);
        response.put("data",getPercentageCompatibility( userService.getUserById(user.getId()) , userService.getUserById(userMatch.getId()) ));
        response.put("message", "Compatibilidad calculada");
        return response;
    }
    /**
     *
     * Funcion para calcular la compatibilidad entre dos usuarios
     *
     * **/
    private HashMap<String,Object> getPercentageCompatibility(User user, User matchUser){
        int compatibility = 0;
        if( user.getSexualOrientation().equals(matchUser.getSexualOrientation()) ){
            compatibility+= 70;
        }
        if( user.getHobbies().equals(matchUser.getHobbies()) ){
            compatibility+= 30;
        }
        response.put("compatibility" , compatibility);
        return response;
    }
}
