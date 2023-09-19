package org.example.repository.impl;

import org.example.model.AnotherEntity;
import org.example.repository.AnotherEntityRepository;

import java.util.List;
import java.util.UUID;

public class AnotherEntityRepositoryImpl  implements AnotherEntityRepository {
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

    @Override
    public List getChild(UUID id) {
        return null;
    }
}
