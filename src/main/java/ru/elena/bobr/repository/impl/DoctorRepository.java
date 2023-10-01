package ru.elena.bobr.repository.impl;

import ru.elena.bobr.db.ConnectionFactory;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.repository.EntityRepository;
import ru.elena.bobr.repository.IParent;
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

public class DoctorRepository implements EntityRepository<Doctor, UUID>, IParent<AnotherEntity> {

    private IResultSetMapper doctorResultSetMapper = new DoctorResultSetMapperImpl();

    private IResultSetMapper anotherResultSetMapper = new AnotherResultSetMapperImpl();

    public  Connection connection = new ConnectionFactory( "/utils/database.properties").getConnection();

    @Override
    public Doctor findById(UUID uuid) {
        Doctor entity = new Doctor();

        try {

            try(PreparedStatement preparedStatement = connection.prepareStatement("select * from doctor where uuid = ?")){
            preparedStatement.setObject(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (Doctor) doctorResultSetMapper.map(resultSet);}
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public boolean deleteById(UUID uuid) {
        List<AnotherEntity> anotherEntities = getChildren(uuid);
        if ( anotherEntities.size()==0 ) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("delete from doctor where uuid = ?");
                preparedStatement.setObject(1, uuid);
                preparedStatement.execute();
                return true;
            } catch (SQLException e) {
                return false;
            }
        } else return false;

    }


    @Override
    public List<Doctor> findAll() {
        try {
            try(PreparedStatement preparedStatement = connection.prepareStatement("select * from simple_entity")){
            ResultSet resultSet = preparedStatement.executeQuery();
            return doctorResultSetMapper.mapList(resultSet);}
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Doctor save(Doctor doctor) {

            try {
                try(PreparedStatement preparedStatement = connection.prepareStatement("insert into doctor ( name, last_name) values( ?, ?)")){
                preparedStatement.setString(1, doctor.getName());
                preparedStatement.setString(2, doctor.getLastName());
                preparedStatement.execute();
                return doctor;
                }
            } catch (SQLException e) {
                return null;
            }
    }

    @Override
    public Doctor update(Doctor doctor) {
        try {
            try(PreparedStatement preparedStatement = connection.prepareStatement("update doctor set name= ?, last_name = ? where uuid = ?")) {
                preparedStatement.setString(1, doctor.getName());
                preparedStatement.setString(2, doctor.getLastName());
                preparedStatement.setObject(3, doctor.getUuid());
                preparedStatement.execute();
                return doctor;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<AnotherEntity> getChildren(UUID simple_uuid) {
        List <AnotherEntity> anotherEntities = new ArrayList<>();
        try {
            try(PreparedStatement preparedStatement = connection.prepareStatement("select name, uuid, simple_uuid from anothers " +
                    "where uuid in ( select pets_uuid  from doctors_pets where doctor_uuid =?) ")){
            preparedStatement.setObject(1, simple_uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            anotherEntities = anotherResultSetMapper.mapList(resultSet);
            return anotherEntities;
            }
        } catch (SQLException e) {
            return anotherEntities;
        }
    }

    @Override
    public boolean deleteAllChildren(UUID parent_uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from doctors_pets where doctor_uuid = ?  ");
            preparedStatement.setObject(1, parent_uuid);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}

