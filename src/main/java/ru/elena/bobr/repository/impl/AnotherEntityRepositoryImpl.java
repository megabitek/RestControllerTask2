package ru.elena.bobr.repository.impl;

import ru.elena.bobr.db.ConnectionFactory;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.mapper.AnotherResultSetMapperImpl;
import ru.elena.bobr.repository.mapper.DoctorResultSetMapperImpl;
import ru.elena.bobr.repository.mapper.IResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnotherEntityRepositoryImpl implements AnotherEntityRepository {

    private IResultSetMapper<AnotherEntity> anotherResultSetMapper = new AnotherResultSetMapperImpl();

    private IResultSetMapper<Doctor> doctorResultSetMapper = new DoctorResultSetMapperImpl();


    public Connection connection = new ConnectionFactory("/utils/database.properties").getConnection();


    @Override
    public AnotherEntity findById(UUID id) {
        AnotherEntity entity = new AnotherEntity();
        try {

           try( PreparedStatement preparedStatement = connection.prepareStatement("select * from anothers where uuid = ?")) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            entity =  anotherResultSetMapper.map(resultSet);
            return entity;}
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(UUID id) {

        try {
try

        (  PreparedStatement preparedStatement = connection.prepareStatement("delete from anothers where uuid = ?")){
            preparedStatement.setObject(1, id);
            preparedStatement.execute();
            return true;}
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<AnotherEntity> findAll() {
        List<AnotherEntity>  allEntities= new ArrayList<>();
        try {
try
        (PreparedStatement preparedStatement = connection.prepareStatement("select * from anothers")) {
    ResultSet resultSet = preparedStatement.executeQuery();
    allEntities = anotherResultSetMapper.mapList(resultSet);}

        } catch (SQLException e) {
            return  allEntities;
        }
        return  allEntities;
    }


    @Override
    public AnotherEntity save(AnotherEntity another) {
        try {

          try(PreparedStatement preparedStatement = connection.prepareStatement("insert into anothers ( name, simple_uuid) values( ?, ?)")){
            preparedStatement.setString(1, another.getName());
            preparedStatement.setObject(2, another.getSimple());
            preparedStatement.execute();
            return another;}
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public AnotherEntity update(AnotherEntity another) {
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement("update anothers  set name = ?, simple_uuid=? where uuid= ?")) {
                preparedStatement.setString(1, another.getName());
                preparedStatement.setObject(2, another.getSimple());
                preparedStatement.execute();
                return another;
            }
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public List<Doctor> getChildren(UUID uuid) {
        List<Doctor> doctors = new ArrayList<>();
        try {

            try (PreparedStatement preparedStatement = connection.prepareStatement("select name, last_name, uuid from doctor " +
                    "where uuid in ( select doctor_uuid  from doctors_pets where pets_uuid =?) ")) {
                preparedStatement.setObject(1, uuid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    doctors = doctorResultSetMapper.mapList(resultSet);
                    return doctors;
                }
            }
        } catch (SQLException e) {
            return   doctors;
        }
    }

    @Override
    public boolean deleteAllChildren(UUID parentUuid) {

        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement("delete from doctors_pets where pets_uuid = ?  ")) {
                preparedStatement.setObject(1, parentUuid);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }

    }
}


