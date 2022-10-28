package com.dating.app.task.dating.controller;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.HashMap;
import java.util.Map;
/***
 *
 *
 *  MATCH CONTROLLER
 *
 *
 * ***/
@RestController
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    private Map<String, Object> response = new HashMap<>();

    /**
     *
     * Funcion para obtener todos los matches del usuario
     *
     * **/
    @GetMapping("/all/user/{id}")
    private ResponseEntity<?> getAllMatches(@PathVariable Long id){
        response.clear();
        try{
            response.put("result" , matchService.getAllMatchesByUser(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }
    /***
     *
     * Funcion para buscar posibles matches por usuario
     *
     * **/
    @GetMapping("/find/user/{id}")
    private ResponseEntity<?> findAllPossibleMatches(@PathVariable Long id){
        response.clear();
        try{
            response.put("result",matchService.searchPossibleMatchesByUser(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }
    /**
     *
     * Funcion para guardar un match
     *
     * **/
    @PostMapping("/save")
    private ResponseEntity<?> saveMatch(@RequestBody Match match){
        response.clear();
        try{
            response.put("result",matchService.saveMatch(match));
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }
    /**
     *
     * Funcion para obtener un porcentaje de compatibilidad con dos usuarios
     *
     * **/
    @GetMapping("/compatibility/user/{id}/usermatch/{id_match}")
    private ResponseEntity<?> getPercentageCompatibility(@PathVariable User id, @PathVariable User id_match){
        response.clear();
        try{
            response.put("result" , matchService.getCompatibilityMatch(id,id_match));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }
}
