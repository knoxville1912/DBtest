package com.example.dbtest.service;

import com.example.dbtest.dao.UserDAO;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(Long id) {
        userDAO.delete(id);
    }

    public Map<String, List<User>> getUsersGroupByMail() {
        return userDAO.getUsersGroupByMail();
    }

    public Map<Character, List<User>> getUsersGroupByFirstLetterInLogin() {
        return userDAO.getUsersGroupByFirstLetterInLogin();
    }

    public Long getCountOfEmail() {
        return userDAO.getCountOfEmail();
    }

    public void batchUpdate(List<User> users) {
        userDAO.batchUpdate(users);
    }

    public void batchInsert(List<User> users) {
        userDAO.batchInsert(users);
    }

    public List<User> getAllUserByLineBySQL(String line) {
        return userDAO.getAllUserByLineBySQL(line);
    }
}
