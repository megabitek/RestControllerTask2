package ru.elena.bobr.service.impl;

import ru.elena.bobr.model.AnotherEntity;

import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;
import ru.elena.bobr.service.ICrudService;
import ru.elena.bobr.service.IParentCrudService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnotherCrudServiceImpl implements IParentCrudService<Doctor, AnotherEntity >, ICrudService< UUID, AnotherEntity>  {

    AnotherEntityRepository repository = new AnotherEntityRepositoryImpl();
    DoctorRepository doctorRepository = new DoctorRepository();

    public void setRepository(AnotherEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnotherEntity save(AnotherEntity entity) {
        return repository.save(entity);
    }

    @Override
    public AnotherEntity findById(UUID uuid) {
        AnotherEntity entity= repository.findById(uuid);
        Optional<AnotherEntity> optFound =  Optional.ofNullable(entity);
        if (optFound.isPresent()){
        List<Doctor>  doctors = repository.getChildren(uuid);
        entity.setDoctors(doctors);
        return entity;
        }
        else throw new RuntimeException("Entity not found by id! ");
    }

    @Override
    public Optional<AnotherEntity> delete(UUID uuid) {

        Optional< AnotherEntity> entityDeleted = Optional.ofNullable(repository.findById(uuid));
         if (entityDeleted.isPresent()){
        List<Doctor>  doctors = repository.getChildren(uuid);
        if (!doctors.isEmpty())
            repository.deleteAllChildren(uuid);
        repository.deleteById(uuid);
         }
        return entityDeleted;
    }


    @Override
    public List<AnotherEntity> findAll() {
        List<AnotherEntity> anotherEntities =  repository.findAll();
        anotherEntities.stream().forEach(entity->entity.setDoctors(repository.getChildren(entity.getUuid())));
        return anotherEntities;
    }


    @Override
    public AnotherEntity update(AnotherEntity entity) {
        try {
            return repository.update(entity);
        } catch (SQLException e) {
        throw new RuntimeException("Update is not complete successfully");}
    }

    @Override
    public AnotherEntity addChildEntity(Doctor childEntity, AnotherEntity entity) {
        doctorRepository.save(childEntity);
        entity.getDoctors().add(childEntity);
        return entity;
    }

    @Override
    public void deleteChildEntity(Doctor children, AnotherEntity parent) {
          repository.deleteAllChildren(parent.getUuid());
    }
}

