package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.dao.UserDao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Service

public class UserServiceImpl implements UserService {



    private UserDao userDao;

    private RoleService roleService;

    private BCryptPasswordEncoder passwordEncoder;



    @Autowired

    public UserServiceImpl(UserDao userDao, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {

        this.userDao = userDao;

        this.roleService = roleService;

        this.passwordEncoder = passwordEncoder;

    }



    @Override

    public List<User> getUsers() {

        return userDao.getUsers();

    }



    @Transactional

    @Override

    public void save(User user) {

        String pass = user.getPassword();

        user.setPassword(passwordEncoder.encode(pass));

        userDao.save(user);

    }



    @Override

    public User findByEmail(String email) {

        return userDao.findByEmail(email);

    }



    @Transactional

    @Override

    public void update(User user) {

        String pass = user.getPassword();

        user.setPassword(passwordEncoder.encode(pass));

        userDao.update(user);

    }



    @Transactional

    @Override

    public void delete(int id) {

        userDao.delete(id);

    }



    @Override

    public boolean exist(String email) {

        return userDao.exist(email);

    }

    @Override
    @Transactional
    public void addNewUser(User user) {
        userDao.save(user);
    }

}