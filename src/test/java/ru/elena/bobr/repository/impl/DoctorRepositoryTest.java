package ru.elena.bobr.repository.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.elena.bobr.db.ConnectionFactory;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class DoctorRepositoryTest {
    private static final String UUIDCorrect = "74ac3d23-6b19-4db4-ad08-ca9469a98e9c";
    private static final String UUIDIncorrect = "7f9e408a-1b30-47f6-aace-482dc0854987";
    private static final String childfree = "9336043d-9b26-4587-bdd7-25dc507b60e4";
   // private static final String simpleUUIDCorrect = "76bde8dd-f961-4653-85f5-bcdc4ac171f0";
   // private static final String simpleUUIDIncorrect = "76bde8dd-9876-5432-85f5-bcdc4ac171f0";

    private static final String nameSaved = "iyri";
    private static final String nameUpdated = "denis";




    DoctorRepository repository = new DoctorRepository();


    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16").withDatabaseName("mydatabase")
            .withUsername("postgres")
            .withInitScript("db-migration.SQL")
            .withPassword("1234");

    @BeforeEach
    void setUp() {
        Connection connection = new ConnectionFactory(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword()).getConnection();
        repository.connection = connection;
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    void test() {
        Assert.assertTrue(postgres.isRunning());
    }

    @Test
    void findById() {
        Doctor found = repository.findById(UUID.fromString(UUIDCorrect));
        Assert.assertNotNull(found);
    }

    @Test
    void findAll() {
        List<Doctor> found = repository.findAll();
        Assert.assertNotNull(found);
        Assert.assertEquals(found.size(), 4);
    }

    @Test
    void findByIdIncorrect() {
        Doctor found = repository.findById(UUID.fromString(UUIDIncorrect));
        Assert.assertNull(found);
    }

    @Test
    void deleteByIdChildfree() {
        if (repository.findById(UUID.fromString(childfree)) != null) {
            Assert.assertTrue(repository.deleteById(java.util.UUID.fromString(childfree)));
        }
    }

    @Test
    void deleteById() {
        UUID uuid = UUID.fromString(UUIDCorrect);
        if ((repository.findById(uuid) != null)&(repository.getChildren(uuid).isEmpty())) {
            Assert.assertTrue(repository.deleteById(uuid));
        }
    }

    @Test
    void deleteByIdIncorrect() {
        if (repository.findById(UUID.fromString(UUIDIncorrect)) != null) {
            Assert.assertFalse(repository.deleteById(java.util.UUID.fromString(UUIDIncorrect)));
        }
    }

    @Test
    void save() {
        Doctor entity = new Doctor();
        entity.setName(nameSaved);
        Doctor entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), entitySaved.getName());
        Assert.assertEquals(entity.getLastName(), entitySaved.getLastName());
    }


    @Test
    void update() {
        Doctor entity = repository.findById(UUID.fromString(UUIDCorrect));
        entity.setName(nameUpdated);
        Doctor entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), nameUpdated);

    }

    @Test
    void getChildren(){
        List<AnotherEntity> pets= repository.getChildren(UUID.fromString(UUIDCorrect));
        Assert.assertNotNull(pets);
        Assert.assertEquals(2, pets.size());
        Assert.assertEquals("tobic", pets.get(1).getName());
        Assert.assertEquals("bob", pets.get(0).getName());
    }

    @Test
    void deleteChildren(){
        Assert.assertTrue(repository.deleteAllChildren(UUID.fromString(UUIDCorrect)));
    }
}



