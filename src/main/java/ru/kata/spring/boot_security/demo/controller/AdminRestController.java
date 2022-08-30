package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")

public class AdminRestController {
    private UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getInfoAboutUser(Principal principal) {
        return new ResponseEntity<>(userService.findByEmail(principal.getName()),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> addUser (@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser (@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser (@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
    }
//
//    @GetMapping("/admin/**")
//    public String showAdmin() {
//        return "admin";
//    }
//
//    @GetMapping("/adminUser")
//    public String showAdminUser() {
//        return "adminUser";
//    }

}