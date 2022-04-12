package com.example.dbtest;

import com.example.dbtest.dao.UserDAO;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DBtestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DBtestApplication.class, args);
    }

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) {
        System.out.println(userDAO.getAll());
        System.out.println(userDAO.getById(5L));
        userDAO.create(new User("raptor", "raptor@mail.ru"));
        userDAO.update(new User("septon", "septik12@yandex.ru"), 3L);
        userDAO.delete(2L);
        System.out.println(userDAO.getAll());
    }
}
