package com.app.toymarket.service.impl;

import com.app.toymarket.entity.AuthenticationProvider;
import com.app.toymarket.entity.User;
import com.app.toymarket.exception.UserNotFoundException;
import com.app.toymarket.repository.UserRepository;
import com.app.toymarket.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplements implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplements(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getRegisteredUser(String name, String password) {
        return userRepository.getUserByNameAndPassword(name, password).orElseThrow(() -> new UserNotFoundException("Такой пользователь не зарегистрирован."));
    }

    @Override
    public void processOAuthPostLogin(String email, String username) {
        User existUser = userRepository.getUserByEmailAndName(email, username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setAuthenticationProvider(AuthenticationProvider.GOOGLE);
            newUser.setName(username);
            newUser.setEmail(email);

            userRepository.save(newUser);
        } else {
            existUser.setAuthenticationProvider(AuthenticationProvider.GOOGLE);
            existUser.setName(username);
            existUser.setEmail(email);

            userRepository.save(existUser);
        }
    }
}

