package org.example.service.impl;

import org.example.model.AnotherEntity;
import org.example.model.SimpleEntity;
import org.example.repository.AnotherEntityRepository;

import org.example.repository.EntityRepository;
import org.example.repository.impl.AnotherEntityRepositoryImpl;
import org.example.service.IService;

import java.util.List;
import java.util.UUID;

public class AnotherServiceImpl<K, S> implements IService<UUID, AnotherEntity>  {

    EntityRepository<AnotherEntity, UUID> repository = new AnotherEntityRepositoryImpl();


    @Override
    public AnotherEntity save(AnotherEntity entity) {
        return repository.save(entity);
    }

    @Override
    public AnotherEntity findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public AnotherEntity delete(UUID uuid) {
        AnotherEntity entity = repository.findById(uuid);
        if (repository.deleteById(uuid))
            return entity;
        return null;
    }

    @Override
    public List<AnotherEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public AnotherEntity update(AnotherEntity entity) {
        return repository.update(entity);
    }
}
