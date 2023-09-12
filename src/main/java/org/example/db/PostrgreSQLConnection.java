package org.example.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class PostrgreSQLConnection implements ConnectionManager{
    @Override
    public Connection getConnection() {
       Connection c = null;
        try {
        Class.forName("org.postgresql.Driver");

            try {
                c = DriverManager
                       .getConnection("jdbc:postgresql://localhost:5432/mydatabase",
                                "postgres", "1234");
                return c;
            } catch (SQLException e) {
                return null;

            }

        } catch ( ClassNotFoundException e) {
        return  null;
        }
        }
}
