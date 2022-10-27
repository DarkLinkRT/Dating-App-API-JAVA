package com.dating.app.task.dating.controller;

import com.dating.app.task.dating.model.Match;
import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    private Map<String, Object> response = new HashMap<>();

    //Listar matches
    @GetMapping("/all/user/{id}")
    private ResponseEntity<?> findAllMatches(@PathVariable Long id){
        response.clear();
        response.put("matches",matchService.getAllMatches(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Listar posibles matches
    @GetMapping("/find/user/{id}")
    private ResponseEntity<?> findAllPossibleMatches(@PathVariable Long id){
        response.clear();
        response.put("matches",matchService.getAllPossibleMatches(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Guardar un match
    @GetMapping("/save")
    private ResponseEntity<?> saveMatch(@RequestBody Match match){
        response.clear();
        matchService.saveMatch(match);
        response.put("message" , "match guardado");
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
