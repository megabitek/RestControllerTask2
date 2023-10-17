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
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AnotherEntityRepositoryTest {
    private static final String UUIDCorrect = "7f9e408a-1b30-47f6-aace-482dc0854115";
    private static final String UUIDIncorrect = "7f9e408a-1b30-47f6-aace-482dc0854987";
    private static final String childfree = "90911230-5678-9101-5555-2d8588ac488e";
    private static final String simpleUUIDCorrect = "76bde8dd-f961-4653-85f5-bcdc4ac171f0";
    private static final String simpleUUIDIncorrect = "76bde8dd-9876-5432-85f5-bcdc4ac171f0";

    private static final String nameSaved = "snejok";
    private static final String nameUpdated = "murka";




    AnotherEntityRepositoryImpl repository = new AnotherEntityRepositoryImpl();


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
        AnotherEntity found = repository.findById(UUID.fromString(UUIDCorrect));
        Assert.assertNotNull(found);
    }

    @Test
    void findAll() {
        List<AnotherEntity> found = repository.findAll();
        Assert.assertNotNull(found);
        Assert.assertEquals(found.size(), 4);
    }

    @Test
    void findByIdIncorrect() {
        AnotherEntity found = repository.findById(UUID.fromString(UUIDIncorrect));
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
        AnotherEntity entity = new AnotherEntity();
        entity.setName(nameSaved);
        entity.setSimple(UUID.fromString(simpleUUIDCorrect));
        AnotherEntity entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), entitySaved.getName());
        Assert.assertEquals(entity.getSimple(), entitySaved.getSimple());
    }

    @Test
    void saveSimpleIncorrect() {
        AnotherEntity entity = new AnotherEntity();
        entity.setName(nameSaved);
        entity.setSimple(UUID.fromString(simpleUUIDIncorrect));
        AnotherEntity entitySaved = repository.save(entity);
        Assert.assertNull(entitySaved);
    }
    @Test
    void update() throws SQLException {
        AnotherEntity entity = repository.findById(UUID.fromString(UUIDCorrect));
        entity.setName(nameUpdated);
        entity.setSimple(UUID.fromString(simpleUUIDCorrect));
        AnotherEntity entitySaved = repository.update(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), nameUpdated);
        Assert.assertEquals(entity.getSimple(), entitySaved.getSimple());
    }
    @Test
    void updateIncorrectSimpleUUID() {
        AnotherEntity entity = repository.findById(UUID.fromString(UUIDCorrect));
        entity.setName(nameUpdated);
        entity.setSimple(UUID.fromString(simpleUUIDIncorrect));
        AnotherEntity entitySaved = repository.save(entity);
        Assert.assertNull(entitySaved);

    }
@Test
    void getChildren(){
    List<Doctor> doctors= repository.getChildren(UUID.fromString(UUIDCorrect));
    Assert.assertNotNull(doctors);
    Assert.assertEquals(1, doctors.size());
    Assert.assertEquals("ivan", doctors.get(0).getName());
    Assert.assertEquals("ivanov", doctors.get(0).getLastName());
    }

    @Test
    void deleteChildren(){
        Assert.assertTrue(repository.deleteAllChildren(UUID.fromString(UUIDCorrect)));
    }

}



