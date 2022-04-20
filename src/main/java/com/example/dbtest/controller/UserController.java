package com.example.dbtest.controller;

import com.example.dbtest.model.User;
import com.example.dbtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping()
    public List<User> getAllUserByLineBySQL(@RequestParam String line) {
        return userService.getAllUserByLineBySQL(line);
    }

    @PostMapping()
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
