package ru.elena.bobr.service.impl;

import ru.elena.bobr.model.AnotherEntity;

import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;
import ru.elena.bobr.service.ICrudService;
import ru.elena.bobr.service.IParentCrudService;

import java.util.List;
import java.util.UUID;

public class AnotherCrudServiceImpl implements IParentCrudService<Doctor, AnotherEntity >, ICrudService< UUID, AnotherEntity>  {

    AnotherEntityRepository repository = new AnotherEntityRepositoryImpl();
    DoctorRepository doctorRepository = new DoctorRepository();

    @Override
    public AnotherEntity save(AnotherEntity entity) {
        return repository.save(entity);
    }

    @Override
    public AnotherEntity findById(UUID uuid) {

        AnotherEntity entity =  repository.findById(uuid);
        List<Doctor>  doctors = repository.getChildren(uuid);
        entity.setDoctors(doctors);
        return entity;
    }

    @Override
    public AnotherEntity delete(UUID uuid) {
        AnotherEntity entity = repository.findById(uuid);
        if ((!entity.getDoctors().isEmpty()&& repository.deleteById(uuid)))
            return entity;
        else
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

    @Override
    public AnotherEntity addChildEntity(Doctor childEntity, AnotherEntity entity) {
        doctorRepository.save(childEntity);
        entity.getDoctors().add(childEntity);
        return entity;
    }

    @Override
    public AnotherEntity deleteChildEntity(Doctor children, AnotherEntity parent) {
         if (repository.deleteAllChildren(parent.getUuid())) return parent ;
         else return null;
    }
}

