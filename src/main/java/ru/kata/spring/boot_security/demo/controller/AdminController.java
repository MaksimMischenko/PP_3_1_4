package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

//@PreAuthorize("hasAuthority('admin')")
@Controller
//@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImp userService;

    @Autowired
    public AdminController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "admin";
    }

    @GetMapping("/addUser")
    public String createUserFrom(User user, Model model) {
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "rolesList") String[] roles) {
        userService.addUser(user, roles);
        return "redirect:/";
    }

    @GetMapping("deleteUser/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

}