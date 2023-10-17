package ru.elena.bobr.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;

import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;

import java.util.*;


import static org.mockito.Mockito.*;


public class AnotherServiceTest {

    @Mock
    AnotherEntityRepositoryImpl repository= Mockito.mock(AnotherEntityRepositoryImpl.class);


    @Mock
    DoctorRepository doctorRepository = Mockito.mock(DoctorRepository.class);
    AnotherCrudServiceImpl service = new AnotherCrudServiceImpl();
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

    }

    @Test
    public void saveEntity(){
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(UUID.randomUUID());
        entity.setSimple(SimpleUUIDCorrect);

        when(repository.save(entity)).thenReturn(entity);
            AnotherEntity saved = service.save(entity);
            Assert.assertNotNull(saved);
            Assert.assertEquals(saved.getName(), entity.getName());
            Assert.assertEquals(saved.getUuid(), entity.getUuid());
            Assert.assertEquals(saved.getSimple(), entity.getSimple());
        }

    @Test
    public void findById() {
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(uuidExist);
        when(repository.findById(uuidExist)).thenReturn(entity);
        AnotherEntity found = service.findById(uuidExist);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), entity.getName());
        Assert.assertEquals(found.getUuid(), entity.getUuid());

    }
    @Test
    public  void delete (){
        AnotherEntity entity = new AnotherEntity();
        entity.setUuid(uuidExist);
        when(repository.findById(uuidExist)).thenReturn(entity);
        when(repository.getChildren(uuidExist)).thenReturn(new ArrayList<Doctor>());
        when(repository.deleteById(uuidExist)).thenReturn(true);
       Assert.assertTrue(service.delete(uuidExist).isPresent());
      //  Assert.assertEquals(entity.getUuid(), deleted.getUuid());
     //   Assert.assertEquals(entity.getName(), deleted.getName());
    }
    @Test
    void findAll(){
        List<AnotherEntity> anotherEntityList = new ArrayList<>();
        when(repository.findAll()).thenReturn(anotherEntityList);
        Assert.assertNotNull(service.findAll());

    }
    @Test
    void deleteAllChildren(){
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(uuidExist);
        List<Doctor> docList = new ArrayList<>();
        docList.add(0, new Doctor( UUID.randomUUID(), "petr", "petrov"));
        entity.setDoctors(docList);
        service.deleteAllChildren(entity);
        Assert.assertEquals(entity.getDoctors().size(), 0);

}
    @Test
    void addChildEntity() {
        AnotherEntity entity = new AnotherEntity();
        entity.setName(NAME);
        entity.setUuid(uuidExist);
        Doctor childEntity = new Doctor();
        childEntity.setName("Fedor");
        childEntity.setUuid(UUID.randomUUID());
        service.addChildEntity( childEntity, entity);
        Assert.assertEquals(entity.getDoctors().size(), 1);
    }


}






