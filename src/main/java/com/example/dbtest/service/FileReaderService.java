package com.example.dbtest.service;

import com.example.dbtest.mapper.UserFileMapper;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.FileWorker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FileReaderService {

    public List<User> collectUsers() {
        File directory = new File("C:\\Users\\Danko\\IdeaProjects\\DBtest\\files");
        List<User> usersList = new ArrayList<>();
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
                try {
                    File file = files[i];
                    usersList = FileWorker.readFile(file, new UserFileMapper());
                } catch (IOException e) {
                    System.err.println("Issue with file");
                }
            }
        }
        return usersList;
    }
}
