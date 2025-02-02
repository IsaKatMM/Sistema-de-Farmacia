/*package com.sistemaDeFarmacia.rest.controller.dao.services;
import com.sistemaDeFarmacia.rest.models.User;
import com.sistemaDeFarmacia.rest.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) throws IOException {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) throws IOException {
        return userRepository.findByEmail(email);
    }

    public void updatePassword(String username, String newPassword) throws IOException {
        List<User> users = userRepository.findAll();
        users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .ifPresent(user -> user.setPassword(newPassword));
        userRepository.saveAll(users);
    }
}*/