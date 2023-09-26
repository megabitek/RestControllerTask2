package org.example.service.impl;

import org.example.model.SimpleEntity;
import org.example.repository.EntityRepository;
import org.example.repository.SimpleEntityRepository;
import org.example.repository.impl.SimpleEntityRepositoryImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.when;

class SimpleServiceImplTest {

    public  final String NAME = "vova";
    public  final UUID uuid = UUID.fromString("76bde8dd-f961-4653-85f5-bcdc4ac171f0");



    SimpleServiceImpl service = new SimpleServiceImpl();

    SimpleEntityRepository repository = Mockito.mock(SimpleEntityRepositoryImpl.class);

    @BeforeEach
    void setUp() {
       service.setRepository(repository);
    }

    @Test
    void save() {
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        when(repository.save(entity)).thenReturn(entity);
        SimpleEntity saved = service.save(entity);
        Assert.assertNotNull(saved);
        Assert.assertEquals(saved.getName(), entity.getName());
        Assert.assertEquals(saved.getUuid(), entity.getUuid());
    }

    @Test
    void findById() {
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(uuid);
        when(repository.findById(uuid)).thenReturn(entity);
        SimpleEntity found = service.findById(uuid);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), entity.getName());
        Assert.assertEquals(found.getUuid(), entity.getUuid());

    }

    @Test
    void delete (){
        when(repository.deleteById(uuid)).thenReturn(true);
        SimpleEntity entity = new SimpleEntity();
        entity.setUuid(uuid);
        SimpleEntity deleted = service.delete(uuid);
        Assert.assertEquals(entity.getUuid(), deleted.getUuid());
        Assert.assertEquals(entity.getName(), deleted.getName());
    }
   }