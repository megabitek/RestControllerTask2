package ru.elena.bobr.repository.mapper;

import ru.elena.bobr.model.AnotherEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AnotherResultSetMapperImpl implements IResultSetMapper<AnotherEntity> {


    @Override
    public AnotherEntity createEntity(ResultSet resultSet) {
        try {
            String name = resultSet.getString("name");
            UUID uuid = (UUID) resultSet.getObject("uuid");
            UUID simpleUuid = (UUID) resultSet.getObject("simple_uuid");
            return new AnotherEntity( uuid,name, simpleUuid );
        } catch (SQLException e) {
            return null;
        }
    }
}
