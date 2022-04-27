package com.example.dbtest;

import com.example.dbtest.dao.UserDAO;
import com.example.dbtest.model.User;
import com.example.dbtest.service.FileReaderService;
import com.example.dbtest.service.FileWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import program.Format;

import java.io.IOException;
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
    @Autowired
    private FileReaderService fileReaderService;
    @Autowired
    private FileWriterService fileWriterService;

    @Override
    public void run(String... args) throws IOException {
        System.out.println(userDAO.getAll());
//        Optional<User> userDAOById = userDAO.getById(3L);
//        if (userDAOById.isPresent()) {
//            System.out.println(userDAOById);
//        }
        System.out.println(userDAO.getCountOfEmail());
        System.out.println(userDAO.getUsersGroupByFirstLetterInLogin());
        System.out.println(fileReaderService.collectUsers());
        fileWriterService.writeFile("C:\\Users\\Danko\\IdeaProjects\\DBtest\\writtenFiles\\writtenUsers", Format.TXT, userDAO.getAll());
    }
}
