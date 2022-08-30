package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
public interface RoleService {
    Role findByRole (String role);

    void saveRole (Role role);

    boolean isRoleExist(String role);

}