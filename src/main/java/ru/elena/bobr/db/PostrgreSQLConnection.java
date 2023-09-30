package ru.elena.bobr.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class PostrgreSQLConnection implements ConnectionManager {

    public String url="jdbc:postgresql://localhost:5432/mydatabase";
    public String user= "postgres";
    public String password ="1234" ;


    private static Connection connection;

    private Connection createConnection(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager
                    .getConnection(url,
                            user, password);
            return connection;
        } catch (SQLException|ClassNotFoundException e) {
            return null;
        }

    }



    @Override
    public  Connection getConnection(String url, String user, String password) {
        if (connection == null)
            return createConnection(url, user, password);
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {

        }
    }
}
