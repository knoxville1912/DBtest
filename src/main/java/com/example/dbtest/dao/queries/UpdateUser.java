package com.example.dbtest.dao.queries;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateUser extends SqlUpdate {
    private static final String sql = "UPDATE Users SET login= :login, email= :email WHERE id= :id";

    public UpdateUser(DataSource ds) {
        super(ds, sql);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        super.declareParameter(new SqlParameter("login", Types.VARCHAR));
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
    }
}
