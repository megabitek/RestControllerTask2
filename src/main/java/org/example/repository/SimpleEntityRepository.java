package org.example.repository;

import org.example.model.SimpleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SimpleEntityRepository extends EntityRepository<SimpleEntity, UUID>, IParent{

    @Override
    public SimpleEntity findById(UUID id) ;

    @Override
    public boolean deleteById(UUID id) ;

    @Override
    public List<SimpleEntity> findAll() ;

    @Override
    public SimpleEntity save(SimpleEntity simpleEntity) ;
    @Override
    public SimpleEntity update(SimpleEntity simpleEntity);



}
