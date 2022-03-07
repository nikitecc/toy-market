package com.app.toymarket.service;

import com.app.toymarket.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    void saveUser(User user);

    User getRegisteredUser(String name, String password);
}
