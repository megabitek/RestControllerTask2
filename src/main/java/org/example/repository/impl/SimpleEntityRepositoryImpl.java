package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.PostrgreSQLConnection;
import org.example.model.SimpleEntity;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.mapper.SimpleResultSetMapper;
import org.example.repository.mapper.SimpleResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SimpleEntityRepositoryImpl implements SimpleEntityRepository {
    private SimpleResultSetMapper resultSetMapper= new SimpleResultSetMapperImpl();
    private ConnectionManager connectionManager = new PostrgreSQLConnection();

    @Override
    public SimpleEntity findById(UUID uuid) {
        // Здесь используем try with resources


        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.map(resultSet);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(UUID uuid) {

        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity where uuid = ?");
            return true;
        } catch (SQLException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;

        }

    }

    @Override
    public List<SimpleEntity> findAll() {

        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapList(resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into simple_entity values(1, 2)");
            preparedStatement.setObject(1, simpleEntity.getUuid());
            preparedStatement.setString(2, simpleEntity.getName());
            return simpleEntity;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
    }
}
