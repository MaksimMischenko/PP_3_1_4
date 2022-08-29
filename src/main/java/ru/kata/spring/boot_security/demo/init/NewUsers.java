package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class NewUsers {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public NewUsers(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {
        Set<Role> set = new HashSet<>();
        Set<Role> set1 = new HashSet<>();
        Set<Role> set2 = new HashSet<>();

        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");

        roleService.saveRole(user);
        roleService.saveRole(admin);

        set.add(admin);
        set1.add(user);

        for (int i = 0; i < 2; i++) {
            userService.saveUser(new User("admin" + i, "surname" + i, "email" + i,
                    i, "1", set));
        }

        for (int i = 3; i < 5; i++) {
            userService.saveUser(new User("user" + i, "surname" + i, "email" + i,
                    i, "1", set1));
        }
    }

}