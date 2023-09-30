package ru.elena.bobr.service.impl;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.SimpleEntityRepository;
import ru.elena.bobr.repository.impl.SimpleEntityRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.Mockito.when;

class SimpleServiceImplTest {

    public   final String NAME = "vova";
    public   final UUID uuid = UUID.fromString("76bde8dd-f961-4653-85f5-bcdc4ac171f0");
    SimpleServiceImpl service = new SimpleServiceImpl();
    SimpleEntityRepository repository = Mockito.mock(SimpleEntityRepositoryImpl.class);

    @BeforeEach
    void setUp() {
       service.setRepository(repository);
    }

    @Before
     void createEntityInRepo(){
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        when(repository.findById(uuid)).thenReturn(entity);
        when(repository.deleteById(uuid)).thenReturn(true);
        when(repository.save(entity)).thenReturn(entity);
    }

    @Test
    void save() {
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
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
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        when(repository.findById(uuid)).thenReturn(entity);
        when(repository.deleteById(uuid)).thenReturn(true);
        SimpleEntity deleted = service.delete(uuid);
        Assert.assertEquals(entity.getUuid(), deleted.getUuid());
        Assert.assertEquals(entity.getName(), deleted.getName());
    }
   }