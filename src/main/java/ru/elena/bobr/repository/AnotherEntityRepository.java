package ru.elena.bobr.repository;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;

import java.util.List;
import java.util.UUID;

public interface AnotherEntityRepository extends EntityRepository<AnotherEntity, UUID>, IParent<Doctor>{

    @Override
    public AnotherEntity findById(UUID id) ;

    @Override
    public boolean deleteById(UUID id) ;

    @Override
    public List<AnotherEntity> findAll();
    @Override
    public AnotherEntity save(AnotherEntity anotherEntity);

    @Override
    public AnotherEntity update(AnotherEntity another);

}
