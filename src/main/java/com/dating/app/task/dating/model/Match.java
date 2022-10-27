package com.dating.app.task.dating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "matches" )
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "user_one")
    private Long userOne;
    @Column( name = "user_two")
    private Long userTwo;
    @Column( name = "created_at")
    private Date createdAt;
}
