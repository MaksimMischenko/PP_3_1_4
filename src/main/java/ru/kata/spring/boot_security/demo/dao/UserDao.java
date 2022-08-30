package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    User findByEmail(String email);

    User getUserByUsername(String username);

    List<User> getUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    boolean isUserExist(String email);

}