package com.example.dbtest.service;

import com.example.dbtest.dao.UserDAO;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User getById(Long id) {
        return userDAO.getById(id).orElseThrow(() -> new RuntimeException("no such user"));
    }

    public User createUser(User user) {
        return userDAO.create(user);
    }

    public List<User> getAllUserByLineBySQL(String line) {
        return userDAO.getAllUserByLineBySQL(line);
    }



}
