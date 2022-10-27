package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
}
