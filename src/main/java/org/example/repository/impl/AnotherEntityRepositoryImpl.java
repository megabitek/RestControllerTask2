package org.example.repository.impl;

import org.example.db.PostrgreSQLConnection;
import org.example.model.AnotherEntity;
import org.example.model.SimpleEntity;
import org.example.repository.AnotherEntityRepository;
import org.example.repository.EntityRepository;
import org.example.repository.mapper.AnotherResultSetMapperImpl;
import org.example.repository.mapper.IResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnotherEntityRepositoryImpl implements EntityRepository<AnotherEntity, UUID> {

    private IResultSetMapper anotherResultSetMapper = new AnotherResultSetMapperImpl();

    public Connection connection = new PostrgreSQLConnection().getConnection("jdbc:postgresql://localhost:5432/mydatabase", "postgres", "1234");

    @Override
    public AnotherEntity findById(UUID id) {
        AnotherEntity entity = new AnotherEntity();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity where uuid = ?");
            preparedStatement.setObject(1, id);
            ResultSet resultSet_sim = preparedStatement.executeQuery();
            entity = (AnotherEntity) anotherResultSetMapper.map(resultSet_sim);
            return entity;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(UUID id) {

            try {

                PreparedStatement preparedStatement = null;
                preparedStatement = connection.prepareStatement("delete from anothers where uuid = ?");
                preparedStatement.setObject(1, id);
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                return false;
            }
    }

    @Override
    public List<AnotherEntity> findAll() {
        return null;
    }

    @Override
    public AnotherEntity save(AnotherEntity another) {
        return null;
    }

    @Override
    public AnotherEntity update(AnotherEntity another) {
        return null;
    }


  /*

    @Override
    public boolean deleteById(UUID id) {
        try {
            PreparedStatement preparedStatement_sim = null;
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("delete from anothers where uuid = ?");
            preparedStatement.setObject(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public List<AnotherEntity> findAll() {
        try {
            List<AnotherEntity> anotherEntities = new ArrayList<AnotherEntity>();
            PreparedStatement preparedStatement_an = connection.prepareStatement("select name, uuid, simple_uuid from anothers");
            ResultSet resultSet_an = null;
            resultSet_an = preparedStatement_an.executeQuery();
            anotherEntities = anotherResultSetMapper.mapList(resultSet_an);
            return anotherEntities;
        } catch (SQLException e) {
            return null;
        }

    }

       @Override
    public AnotherEntity save(AnotherEntity anotherEntity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into anothers ( name, simple_uuid) values( ?, ?)");
            preparedStatement.setString(1, anotherEntity.getName());
            preparedStatement.setObject(2, anotherEntity.getSimple());
            preparedStatement.execute();
            return anotherEntity;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public AnotherEntity update(AnotherEntity another) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update anothers  set name = ?, simple_uuid=? where uuid= ?)");
            preparedStatement.setString(1, another.getName());
            preparedStatement.setObject(2, another.getSimple());
            preparedStatement.execute();
            return another;
        } catch (SQLException e) {
            return null;
        }
    }*/



}
