package com.dating.app.task.dating.service.impl;

import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.model.UserGender;
import com.dating.app.task.dating.model.UserHobbies;
import com.dating.app.task.dating.model.UserSexualOrientation;
import com.dating.app.task.dating.repository.UserRepository;
import com.dating.app.task.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 *  CLASS USER SERVICE
 *
 * ****/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private HashMap<String, Object> response = new HashMap<>();

    /**
     *
     * Funcion para traer todos los usuarios
     *
     * **/
    @Override
    public HashMap<String,Object> getAllUsers() {
        response.put("data", userRepository.findAll());
        response.put("status", true);
        response.put("message", "");
        return response;
    }
    /**
     *
     * Funcion para guardar un usuario
     *
     * **/
    @Override
    public HashMap<String,Object> saveUser(User user) {
        response.put("status", false);
        //Comienza validacion
        if(user.getEmail().equals(null) || user.getEmail().equals("")){
            response.put("message","El EMAIL no puede ir vacio");
            return response;
        }
        if(this.isUserRegistered(user.getEmail())){
            response.put("message","El EMAIL ya se encuentra registrado");
            return response;
        }
        if(user.getFirstName().equals(null) || user.getFirstName().equals("")){
            response.put("message","El PRIMER NOMBRE no puede ir vacio");
            return response;
        }
        if(user.getLastName().equals(null) || user.getLastName().equals("")){
            response.put("message","El APELLIDO no puede ir vacio");
            return response;
        }
        UserGender userGender = user.getGender();
        switch (userGender){
            case FEMALE:
            case MALE:
                break;
            default:
                response.put("message","Solo se aceptan estas opciones para GENERO [female][male]");
                return response;
        }
        UserSexualOrientation userSexualOrientation = user.getSexualOrientation();
        switch (userSexualOrientation){
            case HETEROSEXUAL:
            case HOMOSEXUAL:
            case BISEXUAL:
            case OTHER:
                break;
            default:
                response.put("message","Solo se aceptan estas opciones para ORIENTACION SEXUAL [heterosexual][homosexual][bisexual][other]");
                return response;
        }
        UserHobbies[] userHobbies = user.getHobbies();
        for (UserHobbies hobbie : userHobbies){
            switch (hobbie){
                case WATCH_SERIES:
                case DANCE:
                case SWIM:
                case SING:
                case RUN:
                    break;
                default:
                    response.put("message","Solo se aceptan estas opciones para HOBBIES [watch series][dance][swim][sing][other]");
                    return response;
            }
        }
        //Guarda el usuario despues de validar...
        userRepository.save(user);
        response.put("status", true);
        response.put("message", "Usuario guardado");
        return response;
    }
    /***
     *
     *  Funcion que obtiene los datos de un usuario por ID
     *
     * ***/
    public User getUserById( Long id ){
        return userRepository.findById(id).orElse(null);
    }
    /***
     *
     *  Funcion que valida un usuario registrado por CORREO
     *
     * ***/
    private boolean isUserRegistered( String email ){
        boolean registered = false;
        User user = userRepository.findByEmail(email);
        if(user.getEmail().equals(email)){
            registered = true;
        }
        return registered;
    }
    /****
     *
     * Funcion para validar la existencia de un usuario
     *
     * ***/
    public boolean isUserRegistered( User user ){
        boolean registered = false;
        Optional<User> userFound = userRepository.findById(user.getId());
        if(!userFound.isEmpty()){
            registered = true;
        }
        return registered;
    }
}
