package com.dating.app.task.dating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "first_name")
    private String firstName;
    @Column( name = "last_name")
    private String lastName;
    private String email;
    private UserGender gender;
    @Column( name = "sexual_orientation")
    private UserSexualOrientation sexualOrientation;
    private UserHobbies[] hobbies;
}
