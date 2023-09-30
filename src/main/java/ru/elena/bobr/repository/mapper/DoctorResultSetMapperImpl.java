package ru.elena.bobr.repository.mapper;

import ru.elena.bobr.model.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DoctorResultSetMapperImpl  implements  IResultSetMapper<Doctor>{
    @Override
    public Doctor createEntity(ResultSet resultSet) {
        try {
            String name = resultSet.getString("name");
            String last_name = resultSet.getString("last_name");
            UUID uuid = (UUID) resultSet.getObject("uuid");
            UUID simpleUuid = (UUID) resultSet.getObject("simple_uuid");
            Doctor doctor = new Doctor( uuid, name, last_name );
            return doctor;
        } catch (SQLException e) {
            return null;
        }
    }
}
