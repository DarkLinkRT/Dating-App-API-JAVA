package com.dating.app.task.dating.repository;

import com.dating.app.task.dating.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    /***
     *
     *  ENCONTRAR POSIBLES MATCHES POR USUARIO
     *
     * ***/
    @Query("FROM user WHERE (sexual_orientation = (SELECT sexual_orientation FROM user WHERE id = ?1) OR hobbies = (SELECT hobbies FROM user WHERE id = ?1)) AND id != ?1")
    Match searchPossibleMatchesByUser(Long id);

    /*****
     *
     *  OBTENER TODOS LOS MATCHES DEL USUARIO
     *
     * ****/
    @Query("FROM matches JOIN user ON user.id = matches.user_match_id AND matches.user_id = ?1")
    Match getAllMatchesByUser(Long id);

}
