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
    private Connection connection = new PostrgreSQLConnection().getConnection();



    @Override
    public SimpleEntity findById(UUID uuid) {
        // Здесь используем try with resources


        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.map(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(UUID uuid) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("delete from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;


    }}

    @Override
    public List<SimpleEntity> findAll() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapList(resultSet);
        } catch (SQLException   e) {
            return null;
        }
    }

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into simple_entity ( name) values( ?)");
            preparedStatement.setString(1, simpleEntity.getName());
            preparedStatement.executeQuery();
            return simpleEntity;
        } catch (SQLException  e) {
            return null;
        }
    }

    @Override
    public SimpleEntity update(SimpleEntity simpleEntity){
        try {
        PreparedStatement preparedStatement = connection.prepareStatement("update simple_entity set name= ? where uuid = ?");
        preparedStatement.setString(1, simpleEntity.getName());
        preparedStatement.setObject(2, simpleEntity.getUuid());
        preparedStatement.execute();
        return simpleEntity;
        } catch (SQLException  e) {
            return null;
        }
    }

}
