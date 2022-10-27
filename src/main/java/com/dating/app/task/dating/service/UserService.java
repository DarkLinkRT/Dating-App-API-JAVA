package com.dating.app.task.dating.service;

import com.dating.app.task.dating.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user);
}
