package org.example.repository.impl;

import org.example.model.AnotherEntity;
import org.example.repository.AnotherEntityRepository;
import org.example.repository.mapper.AnotherResultSetMapperImpl;
import org.example.repository.mapper.IResultSetMapper;

import java.util.List;
import java.util.UUID;

public class AnotherEntityRepositoryImpl  implements AnotherEntityRepository {

    private IResultSetMapper anotherResultSetMapper = new AnotherResultSetMapperImpl();
    @Override
    public AnotherEntity findById(UUID id) {
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }

    @Override
    public List<AnotherEntity> findAll() {
        return null;
    }

    @Override
    public AnotherEntity save(AnotherEntity anotherEntity) {
        return null;
    }

    @Override
    public AnotherEntity update(AnotherEntity anotherEntity) {
        return null;
    }


}
