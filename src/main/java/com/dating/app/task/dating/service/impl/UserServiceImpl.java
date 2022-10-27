package com.dating.app.task.dating.service.impl;

import com.dating.app.task.dating.model.User;
import com.dating.app.task.dating.repository.UserRepository;
import com.dating.app.task.dating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private String genders[] = { "male" , "female"};
    private String sexualOrientations[] = { "heterosexual" , "bisexual" , "homosexual" , "other"};
    private String hobbies[] = { "sing" , "dance" , "run" , "swim" , "watch series"};
    //Listar
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Registrar
    @Override
    public void saveUser(User user) {
        /** VALIDACIONES **/

        if(user.getEmail() == null || user.getEmail() == ""){
            new NoSuchElementException("El Email no puede ir vacio");
        } else{
            User user1 = userRepository.findByEmail(user.getEmail());
            if(user1.getEmail() == user.getEmail()){
                new NoSuchElementException("Este correo ya se encuentra registrado");
            }
        }
        if(user.getFirstName() == null || user.getFirstName() == ""){
            new NoSuchElementException("El Primer Nombre no puede ir vacio");
        }
        if(user.getLastName() == null || user.getLastName() == ""){
            new NoSuchElementException("El Apellido no puede ir vacio");
        }

        boolean isGenderCorrect = false;
        for( String gender : this.genders ){
            if( gender == user.getGender() ){
                isGenderCorrect = true;
                break;
            }
        }
        if(!isGenderCorrect){ new NoSuchFieldError("Solo se aceptan estas opciones para Genero [female][male]"); }

        boolean isSexualOrientationCorrect = false;
        for( String orientation : this.sexualOrientations ){
            if( orientation == user.getSexualOrientation() ){
                isSexualOrientationCorrect = true;
                break;
            }
        }
        if(!isSexualOrientationCorrect){ new NoSuchFieldError("Solo se aceptan estas opciones para Orientacion Sexual [heterosexual][bisexual][homosexual][other] "); }

        boolean isHobbieCorrect = true;
        for( String hobbie : this.hobbies ){
            String[] hobbiesUser = user.getHobbies().split(",");
            for(String hobbieUser : hobbiesUser) {
                if( hobbie != user.getHobbies() ){
                    isHobbieCorrect = false;
                    break;
                }
            }
        }
        if(!isHobbieCorrect){ new NoSuchFieldError("Solo se aceptan estas opciones para Hobbie(Separadas por coma si son muchas) [sing][dance][run][swim][watch series]"); }

        userRepository.save(user);
    }
}
