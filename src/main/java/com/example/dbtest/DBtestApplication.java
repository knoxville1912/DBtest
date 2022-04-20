package com.example.dbtest;

import com.example.dbtest.dao.UserDAO;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
public class DBtestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DBtestApplication.class, args);
    }

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) {
//        System.out.println(userDAO.getAll());
//        Optional<User> userDAOById = userDAO.getById(3L);
//        if (userDAOById.isPresent()) {
//            System.out.println(userDAOById);
//        }
//        userDAO.getById(3L).ifPresent(System.out::println);
//        System.out.println(userDAO.create(new User("raptor", "raptor@mail.ru")));
//        userDAO.update(new User(12L, "wqeqwewq", "septik12@yandex.ru"));
//        System.out.println(userDAO.getUsersGroupByMail());
//        System.out.println(userDAO.getUsersGroupByFirstLetterInLoginWithSQL());
//        System.out.println(userDAO.getCountOfEmail());
//        System.out.println(userDAO.findAllUserByLine("ap"));
//        System.out.println(userDAO.getUsersGroupByFirstLetterInLogin());
//        User user1 = new User(5L,"neptun132", "dassdadsasd@mail.ru");
//        User user2 = new User(6L,"mars", "marsohod@yandex.ru");
//        List<User> users = new ArrayList<>();
//        users.add(user1);
//        users.add(user2);
//        userDAO.batchUpdate(users);
//        userDAO.delete(2L);
//        List<User> users2 = new ArrayList<>();
//        User user3 = new User("neon2", "neonoviy@mail.ru");
//        User user4 = new User("tandir", "tundra@yandex.ru");
//        users2.add(user3);
//        users2.add(user4);
//        userDAO.batchInsert(users2);
//        System.out.println(userDAO.getAll());
    }
}
