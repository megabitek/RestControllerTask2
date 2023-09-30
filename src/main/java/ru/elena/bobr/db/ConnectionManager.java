package ru.elena.bobr.db;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

     Connection getConnection(String url, String user, String password) throws SQLException, ClassNotFoundException;
}
