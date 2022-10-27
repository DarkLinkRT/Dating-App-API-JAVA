package com.dating.app.task.dating.controller;

import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Map<String,Object> response = new HashMap<>();
    //Listar usuarios
    @GetMapping("/all")
    private ResponseEntity<?> findAllusers(){
        response.clear();
        response.put("users",userService.getAllUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //Guardar usuario
    @GetMapping("/save")
    private ResponseEntity<?> saveUser(@RequestBody User user){
        response.clear();
        userService.saveUser(user);
        response.put("message" , "usuario guardado");
        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
