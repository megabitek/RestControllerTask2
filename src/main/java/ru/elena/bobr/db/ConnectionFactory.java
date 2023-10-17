package ru.elena.bobr.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class ConnectionFactory {

    private static  Connection connection;
    private String FILE_PROPERTY_NAME= "/database.properties";

    private static  String url;
    private static String user;
    private static String password;


    public ConnectionFactory() {
        Properties properties = new Properties();
        InputStream in = ConnectionFactory.class.getResourceAsStream(FILE_PROPERTY_NAME);
        try {
            properties.load(in);
            this.user = properties.getProperty("database.username");
            this.password = properties.getProperty("database.password");
            this.url = properties.getProperty("database.url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  ConnectionFactory(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() {
        if (connection == null) {
                return createConnection();
        } else {
            return connection;
        }
    }





    private Connection createConnection() {

          try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (SQLException  | ClassNotFoundException ex) {
            System.out.println("Not connection!!! ");
            return null;
        }
    }



}


