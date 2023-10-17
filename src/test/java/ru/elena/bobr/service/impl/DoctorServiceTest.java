package ru.elena.bobr.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.repository.impl.AnotherEntityRepositoryImpl;
import ru.elena.bobr.repository.impl.DoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class DoctorServiceTest {

    @Mock
    DoctorRepository repository = Mockito.mock(DoctorRepository.class);

    DoctorService service = new DoctorService();

    public final UUID uuidExist = UUID.fromString("9336043d-9b26-4587-bdd7-25dc507b60e4");
    public final UUID uuidNotExist = UUID.fromString("1136043d-9b26-4587-bdd7-25dc507b60e4");
    public final String newNAME = "Petr";

    public final String existName = "andrei";

    @BeforeEach
    void setUp() {
        service.setDoctor_repo(repository);
    }

    @Test
    public void saveEntity() {
        Doctor entity = new Doctor();
        entity.setName(newNAME);
        entity.setUuid(UUID.randomUUID());

        //  when(repository.findById(uuidExist)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        Doctor saved = service.save(entity);
        Assert.assertNotNull(saved);
        Assert.assertEquals(saved.getName(), entity.getName());
        Assert.assertEquals(saved.getUuid(), entity.getUuid());
    }

    @Test
    public void findById() {
        Doctor entity = new Doctor();
        entity.setName(existName);
        entity.setUuid(uuidExist);
        when(repository.findById(uuidExist)).thenReturn(entity);
        Doctor found = service.findById(uuidExist);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.getName(), entity.getName());
        Assert.assertEquals(found.getUuid(), entity.getUuid());
    }

    @Test
    public void delete() {
        Doctor entity = new Doctor();
        entity.setUuid(uuidExist);
        when(repository.findById(uuidExist)).thenReturn(entity);
        when(repository.getChildren(uuidExist)).thenReturn(new ArrayList<AnotherEntity>());
        when(repository.deleteById(uuidExist)).thenReturn(true);
       Assert.assertNotNull(service.delete(uuidExist).get());
    }

    @Test
    public void deleteIncorrect(){
        Doctor entity = new Doctor();
        entity.setUuid(uuidNotExist);
        when(repository.findById(uuidExist)).thenReturn(null);
        Assert.assertTrue(service.delete(uuidNotExist).isEmpty());
    }
    @Test
    void findAll(){
        List<Doctor> doctorList = new ArrayList<>();
        when(repository.findAll()).thenReturn(doctorList);
        Assert.assertNotNull(service.findAll());
    }


    @Test
    void update (){
        Doctor entity = new Doctor();
        entity.setName(newNAME);
        entity.setUuid(uuidExist);
        when(repository.update(entity)).thenReturn(entity);
        Assert.assertNotNull(service.update(entity));
    }

    @Test
    void addChildEntity() {
        Doctor entity = new Doctor();
        entity.setName(newNAME);
        entity.setUuid(uuidExist);
        AnotherEntity childEntity = new AnotherEntity();
        childEntity.setName("Murzik");
        childEntity.setSimple(UUID.fromString("11111111-6666-3333-4444-555555555555"));
                service.addChildEntity( childEntity, entity);
                Assert.assertEquals(entity.getPets().size(), 1);
    }
@Test
    void deleteAllChildren(){
    Doctor entity = new Doctor();
    entity.setName(newNAME);
    entity.setUuid(uuidExist);
    List<AnotherEntity> petList = new ArrayList<>();
    petList.add(0, new AnotherEntity( UUID.randomUUID(), "murzik"));
    entity.setPets(petList);
    service.deleteAllChildren(entity);
    Assert.assertEquals(entity.getPets().size(), 0);
}

}