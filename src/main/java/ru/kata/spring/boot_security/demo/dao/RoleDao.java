package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
    Role findByRole(String role);

    void saveRole(Role role);

    boolean isRoleExist(String role);

}