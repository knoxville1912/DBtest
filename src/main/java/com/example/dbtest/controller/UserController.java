package com.example.dbtest.controller;

import com.example.dbtest.model.User;
import com.example.dbtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/group-by-mail")
    public Map<String, List<User>> getUsersGroupByMail() {
        return userService.getUsersGroupByMail();
    }

    @GetMapping("/group-by-first-letter-in-login")
    public Map<Character, List<User>> getUsersGroupByFirstLetterInLogin() {
        return userService.getUsersGroupByFirstLetterInLogin();
    }

    @GetMapping("/count-of-mail")
    public Long getCountOfEmail() {
        return userService.getCountOfEmail();
    }

    @PostMapping ("/batch-update")
    public void batchUpdate(@RequestBody List<User> users) {
        userService.batchUpdate(users);
    }

    @PostMapping("/batch-insert")
    public void batchInsert(@RequestBody List<User> users) {
        userService.batchInsert(users);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {
        userService.updateUser(user);
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
