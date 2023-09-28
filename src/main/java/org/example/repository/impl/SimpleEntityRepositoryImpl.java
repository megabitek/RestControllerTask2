package org.example.repository.impl;

import org.example.db.PostrgreSQLConnection;
import org.example.model.AnotherEntity;
import org.example.model.SimpleEntity;
import org.example.repository.EntityRepository;
import org.example.repository.IParent;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.mapper.AnotherResultSetMapperImpl;
import org.example.repository.mapper.IResultSetMapper;
import org.example.repository.mapper.SimpleResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleEntityRepositoryImpl implements SimpleEntityRepository, IParent {
    private IResultSetMapper simpleResultSetMapper = new SimpleResultSetMapperImpl();
    private IResultSetMapper anotherResultSetMapper = new AnotherResultSetMapperImpl();
    public Connection connection = new PostrgreSQLConnection().getConnection("jdbc:postgresql://localhost:5432/mydatabase", "postgres", "1234");

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SimpleEntity findById(UUID uuid) {
        // Здесь используем try with resources

        SimpleEntity entity = new SimpleEntity();
        List<AnotherEntity> anotherEntities = new ArrayList<>();
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
    public boolean deleteById(UUID uuid){
        List<AnotherEntity> anotherEntities = getChildren(uuid);
if ( anotherEntities.size()==0 ) {
         try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from simple_entity where uuid = ?");
            preparedStatement.setObject(1, uuid);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
             return false;
         }
        } else return false;

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
        List <AnotherEntity> anotherEntities = anotherResultSetMapper.mapList(resultSet);
        return anotherEntities;
    } catch (SQLException e) {
        return null;
    }
}
}
