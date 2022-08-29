package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void saveUser(User user);

    User findByEmail(String email);

    void updateUser(User user);

    void deleteUser(int id);

    boolean isUserExist(String email);

}