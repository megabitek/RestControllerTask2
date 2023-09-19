package org.example.repository.mapper;

import org.example.model.SimpleEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface SimpleResultSetMapper<T> {


     default  T map(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                return createEntity(resultSet);
            } else return null;
        } catch (SQLException ex) {
            return null;
        }

    }

    ;

    default  List<T> mapList(ResultSet resultSet) {
        try {
            List<T> entityList = new ArrayList<T>();
            while (resultSet.next()) {
                T entity = createEntity(resultSet);
                entityList.add(entity);
            }
            return entityList;

        } catch (SQLException ex) {
            return null;
        }

    }

    T createEntity(ResultSet resultSet);
}