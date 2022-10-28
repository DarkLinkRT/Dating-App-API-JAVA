package com.dating.app.task.dating.controller;

import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * USER CONTROLLER
 *
 * */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Map<String,Object> response = new HashMap<>();

    /**
     *
     * Funcion para traer a todos los usuarios
     *
     * **/
    @GetMapping("/all")
    private ResponseEntity<?> getAllusers(){
        response.clear();
        try {
            response.put("result",userService.getAllUsers());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }
    /**
     *
     * Funcion para guardar un usuario
     *
     * **/
    @PostMapping("/save")
    private ResponseEntity<?> saveUser(@RequestBody User user){
        response.clear();
        try {
            response.put("result",userService.saveUser(user));
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED, "an error ocurred", e
            );
        }
    }

}
