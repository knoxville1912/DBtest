package com.example.dbtest.extractor;

import com.example.dbtest.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserGroupByFirstLetterInLoginExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("email"))
            );
        }
        users.sort(Comparator.comparing(User::getLogin));
        return users;
    }
}
