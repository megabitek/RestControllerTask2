package org.example.repository.mapper;

import org.example.model.SimpleEntity;

import java.sql.ResultSet;
import java.util.List;

public interface SimpleResultSetMapper {
    SimpleEntity map(ResultSet resultSet);
    List<SimpleEntity> mapList(ResultSet resultSet);}
