package org.example.repository.mapper;

import org.example.model.SimpleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleResultSetMapperImpl implements SimpleResultSetMapper<SimpleEntity> {


    public SimpleEntity createEntity(ResultSet resultSet) {

        try {
            String name = resultSet.getString("name");
            UUID uuid = (UUID) resultSet.getObject("uuid");
            SimpleEntity simpleEntity = new SimpleEntity(uuid,name );
            return simpleEntity;
        } catch (SQLException e) {
            return null;
        }
    }
}

