package com.example.dbtest.mapper;

import com.example.dbtest.model.User;
import program.Mapper;

public class UserFileMapper implements Mapper<User> {
    @Override
    public User map(String[] params) {
        return new User(
                Long.parseLong(params[0]),
                params[1],
                params[2]
        );
    }
}
