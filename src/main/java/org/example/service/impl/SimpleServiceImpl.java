package org.example.service.impl;

import org.example.model.SimpleEntity;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.impl.SimpleEntityRepositoryImpl;
import org.example.service.SimpleService;

import java.util.List;
import java.util.UUID;

public class SimpleServiceImpl implements SimpleService {

    SimpleEntityRepository repository = new SimpleEntityRepositoryImpl();

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {
        repository.save(simpleEntity);
        return simpleEntity;
    }

    @Override
    public SimpleEntity findById(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public SimpleEntity delete(UUID uuid) {
        return null;
    }

    @Override
    public List<SimpleEntity> findAll() {
        return null;
    }

    @Override
    public SimpleEntity update(UUID uuid) {
        return null;
    }


}
