package org.example;

import org.example.db.ConnectionManager;
import org.example.db.PostrgreSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager= new PostrgreSQLConnection();
        try {
            Connection connection= connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("name");
System.out.println(id+ " "+ name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}