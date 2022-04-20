package com.example.dbtest.extractor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCountOfEmail implements ResultSetExtractor<Long> {
    @Override
    public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
        Long count = 0L;
        while (rs.next()) {
            if (!rs.getString("email").isEmpty()) {
                count++;
            }
        }
        return count;
    }
}
