package org.example.repository;

import org.example.model.SimpleEntity;

import java.util.List;
import java.util.UUID;

public interface SimpleEntityRepository extends EntityRepository<SimpleEntity, UUID>{

    @Override
    public SimpleEntity findById(UUID id) ;

    @Override
    public boolean deleteById(UUID id) ;

    @Override
    public List<SimpleEntity> findAll() ;

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) ;

    public SimpleEntity update(SimpleEntity simpleEntity);
}
