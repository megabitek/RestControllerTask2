package ru.elena.bobr.repository.impl;

import ru.elena.bobr.db.ConnectionFactory;
//import ru.elena.bobr.db.PostrgreSQLConnection;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.IParent;
import ru.elena.bobr.repository.SimpleEntityRepository;
import ru.elena.bobr.repository.mapper.AnotherResultSetMapperImpl;
import ru.elena.bobr.repository.mapper.IResultSetMapper;
import ru.elena.bobr.repository.mapper.SimpleResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SimpleEntityRepositoryImpl implements SimpleEntityRepository, IParent<AnotherEntity> {
    private IResultSetMapper simpleResultSetMapper = new SimpleResultSetMapperImpl();
    private IResultSetMapper anotherResultSetMapper = new AnotherResultSetMapperImpl();

    public  Connection connection = new ConnectionFactory( ).getConnection();




    @Override
    public SimpleEntity findById(UUID uuid) {
        SimpleEntity entity = new SimpleEntity();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            entity = (SimpleEntity) simpleResultSetMapper.map(resultSet);
            return entity;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(UUID uuid) {
        List<AnotherEntity> anotherEntities = getChildren(uuid);
        if (anotherEntities.size() != 0)
            return false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;

        }
    }


    @Override
    public List<SimpleEntity> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity");
            ResultSet resultSet = preparedStatement.executeQuery();
            return simpleResultSetMapper.mapList(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into simple_entity ( name) values( ?)");
            preparedStatement.setString(1, simpleEntity.getName());
            preparedStatement.execute();
            return simpleEntity;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public SimpleEntity update(SimpleEntity simpleEntity) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update simple_entity set name= ? where uuid = ?");
            preparedStatement.setString(1, simpleEntity.getName());
            preparedStatement.setObject(2, simpleEntity.getUuid());
            preparedStatement.execute();
            return simpleEntity;
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public List<AnotherEntity> getChildren(UUID simple_uuid) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select name, uuid, simple_uuid from anothers where simple_uuid =?");
            preparedStatement.setObject(1, simple_uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AnotherEntity> anotherEntities = anotherResultSetMapper.mapList(resultSet);
            return anotherEntities;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteAllChildren(UUID parent_uuid) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("delete from anothers where simple_uuid = ?  ");
            preparedStatement.setObject(1, parent_uuid);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {

            return false;
        }

    }

}
