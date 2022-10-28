package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.User;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
/***
 *
 *  USER SERVICE INTERFACE
 *
 * **/
@Service
public interface UserService {
    /**
     *
     * Funcion para traer todos los usuarios
     *
     * **/
    HashMap<String,Object> getAllUsers();
    /**
     *
     * Funcion para guardar un usuario
     *
     * **/
    HashMap<String,Object> saveUser(User user);
    /****
     *
     * Funcion para validar la existencia de un usuario POR ID
     *
     * ***/
    boolean isUserRegistered(User user);
    /***
     *
     *  Funcion que obtiene los datos de un usuario por ID
     *
     * ***/
    User getUserById( Long id );
}
