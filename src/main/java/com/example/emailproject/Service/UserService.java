package com.example.emailproject.Service;

import com.example.emailproject.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUser(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    User findByEmail(String email);
}
