package com.example.dbtest.extractor;

import com.example.dbtest.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserGroupByFirstLetterInLoginExtractor implements ResultSetExtractor<Map<Character, List<User>>> {
    @Override
    public Map<Character, List<User>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<Character, List<User>> data = new HashMap<>();
        while (rs.next()) {
            char letter = rs.getString("login").toLowerCase(Locale.ROOT).charAt(0);
            List<User> users = data.get(letter);
            if (users == null) {
                users = new ArrayList<>();
            }
            users.add(new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    rs.getString("email")
            ));
            data.put(letter, users);
        }
        return data;
    }
}
