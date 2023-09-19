package org.example.repository;

import org.example.model.AnotherEntity;

import java.util.List;
import java.util.UUID;

public interface AnotherEntityRepository extends EntityRepository<AnotherEntity, UUID>{

    @Override
    public AnotherEntity findById(UUID id) ;

    @Override
    public boolean deleteById(UUID id) ;

    @Override
    public List<AnotherEntity> findAll() ;

    @Override
    public AnotherEntity save(AnotherEntity anotherEntity) ;

    public AnotherEntity update(AnotherEntity anotherEntity);
}
