package com.example.dbtest.service;

import com.example.dbtest.model.User;
import org.springframework.stereotype.Service;
import program.FileWorker;
import program.Format;

import java.io.IOException;
import java.util.List;

@Service
public class FileWriterService {
    public void writeFile(String fileName, Format format, List<User> userList) throws IOException {
        FileWorker.writeFile(fileName, format, userList);
    }
}
