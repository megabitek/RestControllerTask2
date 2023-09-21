package org.example.repository.mapper;

import org.example.model.AnotherEntity;

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
            AnotherEntity another = new AnotherEntity( uuid,name, simpleUuid );
            return another;
        } catch (SQLException e) {
            return null;
        }
    }
}
