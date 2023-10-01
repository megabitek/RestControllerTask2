package ru.elena.bobr.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.AnotherEntityRepository;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class AnotherServiceTest {

   AnotherCrudServiceImpl service = new AnotherCrudServiceImpl();
   AnotherEntityRepository repository = Mockito.mock(AnotherEntityRepositoryImpl.class);

    public   final UUID uuidExist= UUID.fromString("7f9e408a-1b30-47f6-aace-482dc0854115");


    public static final UUID SimpleUUIDCorrect = UUID.fromString("76bde8dd-f961-4653-85f5-bcdc4ac171f0");
    public   final String NAME = "murka";

    @BeforeEach
    void setUp() {
        service.setRepository(repository);
    }

    @Before
    public void createEntityInRepo() {
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        entity.setSimple(SimpleUUIDCorrect);
        when(repository.findById(uuidExist)).thenReturn(entity);
        when(repository.deleteById(uuidExist)).thenReturn(true);
        when(repository.save(entity)).thenReturn(entity);
    }

    @Test
    public void saveEntity(){
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        entity.setSimple(SimpleUUIDCorrect);
            AnotherEntity saved = service.save(entity);
            Assert.assertNotNull(saved);
            Assert.assertEquals(saved.getName(), entity.getName());
            Assert.assertEquals(saved.getUuid(), entity.getUuid());
            Assert.assertEquals(saved.getSimple(), entity.getSimple());
        }

    @Test
    void findById() {
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(uuidExist);
        when(repository.findById(uuidExist)).thenReturn(entity);
        AnotherEntity found = service.findById(uuidExist);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), entity.getName());
        Assert.assertEquals(found.getUuid(), entity.getUuid());

    }

}






