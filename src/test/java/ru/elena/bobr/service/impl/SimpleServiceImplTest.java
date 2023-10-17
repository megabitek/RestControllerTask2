package ru.elena.bobr.service.impl;

import org.junit.Assert;
import org.junit.Before;
/*import org.junit.Test;*/
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.SimpleEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;
import ru.elena.bobr.repository.impl.SimpleEntityRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

class SimpleServiceImplTest {

    public   final String NAME = "vova";
    public   final UUID uuid = UUID.fromString("76bde8dd-f961-4653-85f5-bcdc4ac171f0");
    SimpleServiceImpl service = new SimpleServiceImpl();
    SimpleEntityRepository repository = Mockito.mock(SimpleEntityRepositoryImpl.class);
 //  AnotherEntityRepositoryImpl anRepository = Mockito.mock(AnotherEntityRepositoryImpl.class);
    @BeforeEach
    void setUp() {
       service.setRepository(repository/*, anRepository*/);
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
        Assert.assertNotNull(service.delete(uuid).get());
        //    Assert.assertEquals(entity.getUuid(), deleted.getUuid());
     //   Assert.assertEquals(entity.getName(), deleted.getName());
    }
    @Test
    void update (){
        SimpleEntity entity = new SimpleEntity();
        entity.setName("sdw");
        entity.setUuid(uuid);
        when(repository.update(entity)).thenReturn(entity);
        Assert.assertNotNull(service.update(entity));
    }
    @Test
    void findAll(){
        List<SimpleEntity> simpleEntityList = new ArrayList<>();
        when(repository.findAll()).thenReturn(simpleEntityList);
        Assert.assertNotNull(service.findAll());
    }
    @Test
    void addChildEntity() {
        SimpleEntity entity = new SimpleEntity();
        entity.setName(NAME);
        entity.setUuid(uuid);
        AnotherEntity childEntity = new AnotherEntity();
        childEntity.setName("Murzik");
        childEntity.setSimple(UUID.fromString("11111111-6666-3333-4444-555555555555"));
        service.addChildEntity( childEntity, entity);
        Assert.assertEquals(entity.getOthers().size(), 1);
    }
   }