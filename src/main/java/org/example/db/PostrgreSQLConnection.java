package org.example.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class PostrgreSQLConnection implements ConnectionManager {

    private static Connection connection;

    private Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/mydatabase",
                            "postgres", "1234");
            return connection;
        } catch (SQLException|ClassNotFoundException e) {
            return null;
        }

    }

    ;

    @Override
    public  Connection getConnection() {
        if (connection == null)
            return createConnection();
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
