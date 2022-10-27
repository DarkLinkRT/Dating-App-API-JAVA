package com.dating.app.task.dating.repository;

import com.dating.app.task.dating.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Encontrar email existente
    @Query("SELECT email FROM user WHERE email = ?1")
    User findByEmail(String email);

}
