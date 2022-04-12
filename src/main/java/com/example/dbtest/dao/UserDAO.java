package com.example.dbtest.dao;

import com.example.dbtest.mapper.UserMapper;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public List<User> getAll() {
        return template.query(
                "SELECT * FROM Users",
                new UserMapper()
        );
    }

    public Optional<User> getById(Long id) {
//        template.query("SELECT * FROM Users WHERE id=?", new Object[]{id}, new UserMapper());
//        return template.query("SELECT * FROM Users WHERE id=:id", new MapSqlParameterSource()
//                .addValue("id", id),
//                (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("login"),
//                        rs.getString("email")));
        return template.queryForStream(
                "SELECT * FROM Users", new MapSqlParameterSource(),
                new UserMapper()).filter(user -> user.getId().equals(id)).findFirst();
    }

    public void create(User user) {
        template.update("INSERT INTO Users (login, email) VALUES (:login,:email)", new MapSqlParameterSource()
                        .addValue("login", user.getLogin())
                        .addValue("email", user.getEmail()),
                new GeneratedKeyHolder());
    }

    public void update(User user, Long id) {
        template.update("UPDATE Users SET login= :login, email= :email WHERE id= :id", new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("email", user.getEmail())
                .addValue("id", id));
    }

    public void delete(Long id) {
        template.update("DELETE FROM Users WHERE id= :id", new MapSqlParameterSource()
                .addValue("id", id));
    }
}
