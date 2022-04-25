package com.example.dbtest.dao;

import com.example.dbtest.dao.queries.UpdateUser;
import com.example.dbtest.extractor.UserGroupByFirstLetterInLoginExtractor;
import com.example.dbtest.extractor.UserGroupByMailExtractor;
import com.example.dbtest.mapper.UserMapper;
import com.example.dbtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private DataSource dataSource;

    public List<User> getAll() {
        return template.query(
                "SELECT * FROM Users",
                new UserMapper()
        );
    }

    public Optional<User> getById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        User user;
        try {
            user = template.queryForObject(
                    "SELECT * FROM Users WHERE id= :id",
                    params,
                    new UserMapper()
            );
            return Optional.of(user);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    public User create(User user) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        template.update("INSERT INTO Users (login, email) VALUES (:login,:email)",
                new MapSqlParameterSource()
                        .addValue("login", user.getLogin())
                        .addValue("email", user.getEmail()),
                generatedKeyHolder);
        Integer id = (Integer) generatedKeyHolder.getKeys().get("id");
        user.setId(Long.valueOf(id));
        return user;
    }

    public void update(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("id is not been null");
        }
        new UpdateUser(dataSource).updateByNamedParam(initParams(user));
    }

    public void delete(Long id) {
        template.update("DELETE FROM Users WHERE id= :id",
                new MapSqlParameterSource()
                        .addValue("id", id));
    }

    /**
     * требуется сгруппировать пользователей по доменам: яндекс, гугл, а остальное исключить
     */
    public Map<String, List<User>> getUsersGroupByMail() {
        return template.query(
                "SELECT * FROM Users WHERE email LIKE '%yandex%' OR email LIKE '%gmail%'",
                new UserGroupByMailExtractor()
        );
    }

    /**
     * требуется сгруппировать пользователей по первой букве Logina
     */
    public Map<Character, List<User>> getUsersGroupByFirstLetterInLogin() {
        return template.query(
                "SELECT * FROM Users",
                new UserGroupByFirstLetterInLoginExtractor()
        );
    }

    /**
     *Количество всех email
     */
    public Long getCountOfEmail() {
        return template.queryForObject(
                "SELECT count(DISTINCT email) FROM Users",
                new MapSqlParameterSource(),
                Long.class
        );
    }

    /**
     *Получение списка юзеров по частичному поиску
     */
    public List<User> getAllUserByLineBySQL(String line) {
        return template.query(
                "SELECT * FROM Users WHERE login LIKE concat('%', :line, '%') OR email LIKE concat('%', :line, '%')",
                new MapSqlParameterSource().addValue("line", line),
                new UserMapper()
        );
    }

    /**
     *Обновление юзеров
     */
    public void batchUpdate(List<User> users) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(users.toArray());
        template.batchUpdate("UPDATE Users SET login= :login, email= :email WHERE id= :id", batch);
    }

    /**
     * вставка юзеров
     */
    public void batchInsert(List<User> users) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(users.toArray());
        template.batchUpdate("INSERT INTO Users (login, email) VALUES (:login,:email)", batch);
    }


    /**
     * реализовать обновление юзеров пачкой batch +
     * реализовать через batchUpdate +
     *
     * реализовать batchInsert +
     *
     * группируем по первой букве Logina -
     *
     * найдем количество всех email через SQL -
     *
     * получение списка юзеров по частичному поиску(на вход строка, как логин так и почта, но может быть не полная) +
     */


    private Map<String, Object> initParams(User user) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        params.put("login", user.getLogin());
        params.put("email", user.getEmail());
        return params;
    }

}
