package org.example.service.impl;

import org.example.model.AnotherEntity;
import org.example.model.SimpleEntity;
import org.example.repository.EntityRepository;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.impl.AnotherEntityRepositoryImpl;
import org.example.repository.impl.SimpleEntityRepositoryImpl;
import org.example.service.ISimpleService;


import java.util.List;
import java.util.UUID;

public class SimpleServiceImpl implements ISimpleService {

    SimpleEntityRepository repository = new SimpleEntityRepositoryImpl();
   // AnotherEntityRepository <AnotherEntity> another_repo = new AnotherEntityRepositoryImpl();
   AnotherEntityRepositoryImpl another_repo = new AnotherEntityRepositoryImpl();
    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) {
        repository.save(simpleEntity);
        return simpleEntity;
    }

    @Override
    public SimpleEntity findById(UUID uuid) {
        SimpleEntity entity=repository.findById(uuid);
        List<AnotherEntity> anotherEntities = repository.getChildren(uuid);
        entity.setOthers(anotherEntities);
        return entity;
    }

    @Override
    public SimpleEntity delete(UUID uuid) {
        SimpleEntity entity = repository.findById(uuid);
        if (entity.getOthers().size()>0)
            entity.getOthers().stream().forEach(other->another_repo.deleteById(other.getUuid()));
        if (repository.deleteById(uuid)){
            return entity;
        }
        else return null;
    }


    @Override
    public List<SimpleEntity> findAll() {
        List<SimpleEntity> all = repository.findAll();
        return all;
    }


    @Override
    public SimpleEntity update(SimpleEntity simpleEntity) {
        SimpleEntity entity = repository.update(simpleEntity);
        return simpleEntity;
    }

    public void setRepository(SimpleEntityRepository repository) {
        this.repository = repository;
    }
}
