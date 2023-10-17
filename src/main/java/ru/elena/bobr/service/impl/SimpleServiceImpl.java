package ru.elena.bobr.service.impl;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.SimpleEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.SimpleEntityRepositoryImpl;
import ru.elena.bobr.service.ICrudService;
import ru.elena.bobr.service.IParentCrudService;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleServiceImpl implements IParentCrudService<AnotherEntity, SimpleEntity>, ICrudService< UUID, SimpleEntity> {

    SimpleEntityRepository repository = new SimpleEntityRepositoryImpl();
   AnotherEntityRepositoryImpl another_repo = new AnotherEntityRepositoryImpl();


    public void setRepository(SimpleEntityRepository repository/*, AnotherEntityRepositoryImpl another_repo*/) {
        this.repository = repository;
    /*    this.another_repo = another_repo;*/
    }


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
    public Optional<SimpleEntity> delete(UUID uuid) {
        Optional<SimpleEntity>  optionalDeleted= Optional.empty();
        SimpleEntity entity = repository.findById(uuid);
        if (entity.getOthers().size()>0)
            entity.getOthers().stream().forEach(other->another_repo.deleteById(other.getUuid()));
        if (repository.deleteById(uuid)){

            return optionalDeleted.ofNullable(entity);
        }
        else return optionalDeleted;
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

    @Override
    public SimpleEntity addChildEntity(AnotherEntity childEntity, SimpleEntity parentEntity) {
        another_repo.save(childEntity);
        parentEntity.getOthers().add(childEntity);
        return parentEntity;
    }



    @Override
    public void deleteAllChildren(SimpleEntity parent) {
repository.deleteAllChildren(parent.getUuid());
parent.getOthers().clear();
    }


}
