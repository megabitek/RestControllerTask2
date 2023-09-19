package org.example.repository.mapper;

import org.example.model.SimpleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleResultSetMapperImpl implements SimpleResultSetMapper {
    @Override
    public SimpleEntity map(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return createEntity(resultSet);
            } else return null;
        } catch (SQLException ex) {
            return null;
        }


    }

    public List<SimpleEntity> mapList(ResultSet resultSet) {
        try {
            List<SimpleEntity> entityList = new ArrayList<SimpleEntity>();
            while (resultSet.next()) {
               SimpleEntity simpleEntity = createEntity(resultSet);
                entityList.add(simpleEntity);
            }
            return entityList;

        } catch (SQLException ex) {
            return null;
        }

    }

    private SimpleEntity createEntity(ResultSet resultSet) {

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

