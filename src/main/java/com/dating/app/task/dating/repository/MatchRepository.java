package com.dating.app.task.dating.repository;

import com.dating.app.task.dating.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    //Encontrar posibles matches por usuario
    @Query("SELECT * FROM Users WHERE (sexualOrientation = (SELECT sexualOrientation FROM Users WHERE id = ?1) OR hobbies = (SELECT hobbies FROM Users WHERE id = ?1)) AND id != ?1")
    Match searchPossibleMatchesByUser(Long id);

    //Encontrar matches por usuario
    @Query("SELECT * FROM match JOIN user ON user.id = match.UserTwo AND match.UserOne = ?1")
    Match searchMatchesByUser(Long id);

}
