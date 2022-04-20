package com.example.dbtest.extractor;

import com.example.dbtest.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroupByMailExtractor implements ResultSetExtractor<Map<String, List<User>>> {
    @Override
    public Map<String, List<User>> extractData(ResultSet rs) throws SQLException, DataAccessException {
        HashMap<String, List<User>> data = new HashMap<>();
        while (rs.next()) {
            String email = rs.getString("email");
            String group;
            if (email.contains("gmail")) {
                group = "google";
            } else {
                group = "yandex";
            }
            List<User> users = data.get(group);
            if (users == null) {
                users = new ArrayList<>();
            }
            users.add(new User(
                    rs.getLong("id"),
                    rs.getString("login"),
                    email)
            );
            data.put(group, users);
        }
        return data;
    }
}
