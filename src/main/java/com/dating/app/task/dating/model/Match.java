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
    @ManyToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Column(name = "user_id")
    private User userId;
    @ManyToMany
    @JoinColumn(name = "user_match_id", referencedColumnName = "id")
    @Column(name = "user_match_id")
    private User userMatchId;
    @Column( name = "created_at")
    private Date createdAt;
}
