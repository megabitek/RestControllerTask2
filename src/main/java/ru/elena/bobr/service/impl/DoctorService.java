package ru.elena.bobr.service.impl;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;
import ru.elena.bobr.service.ICrudService;
import ru.elena.bobr.service.IParentCrudService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DoctorService  implements IParentCrudService<AnotherEntity, Doctor>, ICrudService<UUID, Doctor> {



    DoctorRepository doctor_repo = new DoctorRepository();
    AnotherEntityRepository an_repo = new AnotherEntityRepositoryImpl();

    @Override
    public Doctor save(Doctor entity) {
        return doctor_repo.save(entity);
    }

    @Override
    public Doctor findById(UUID uuid) {
        Doctor entity=doctor_repo.findById(uuid);
        List<AnotherEntity> anotherEntities = doctor_repo.getChildren(uuid);
        entity.setPets(anotherEntities);
        return entity;
    }

    @Override
    public Optional<Doctor> delete(UUID uuid) {
        Doctor entity = doctor_repo.findById(uuid);
        if (entity.getPets().size()>0)
            doctor_repo.deleteAllChildren(uuid);
        if (doctor_repo.deleteById(uuid)){
            return Optional.ofNullable(entity);
        }
        else return Optional.empty();
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> all = doctor_repo.findAll();
        return all;
    }

    @Override
    public Doctor update(Doctor entity) {
        Doctor entity_upd = doctor_repo.update(entity);
        return entity_upd;
    }

    @Override
    public Doctor addChildEntity(AnotherEntity childEntity, Doctor parentEntity) {
        an_repo.save(childEntity);
        parentEntity.getPets().add(childEntity);
        return parentEntity;
    }

    @Override
    public void deleteChildEntity(AnotherEntity children, Doctor parent) {
        doctor_repo.deleteAllChildren(parent.getUuid());
    }
}

