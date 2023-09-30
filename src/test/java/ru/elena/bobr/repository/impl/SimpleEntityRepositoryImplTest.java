package ru.elena.bobr.repository.impl;

import org.junit.Before;
import org.mockito.Mockito;
import ru.elena.bobr.db.ConnectionFactory;
import ru.elena.bobr.db.PostrgreSQLConnection;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import ru.elena.bobr.repository.SimpleEntityRepository;

import java.sql.Connection;
import java.util.List;




class SimpleEntityRepositoryImplTest {

    public static final String UUID = "76bde8dd-f961-4653-85f5-bcdc4ac171f0";
    public static final String childfree = "11111111-6666-3333-4444-555555555555";
    public static final String UUID_incorrect = "89898989-f961-4653-85f5-bcdc4ac171f0";

    SimpleEntityRepositoryImpl repository= new SimpleEntityRepositoryImpl();


    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16").withDatabaseName("mydatabase")
                    .withUsername("postgres")
                    .withInitScript("db-migration.SQL")
                    .withPassword("1234");

    @BeforeEach
    void setUp() {
        Connection connection = new ConnectionFactory(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword()).getConnection();
        repository.connection =connection;

      /*  Connection connection = new PostrgreSQLConnection().getConnection
                (postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
        repository.connection =connection;
    */}

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
        SimpleEntity found = repository.findById(java.util.UUID.fromString(UUID));
        Assert.assertNotNull(found);
    }

    @Test
    void findByWrongId() {
        SimpleEntity found = repository.findById(java.util.UUID.fromString(UUID_incorrect));
        Assert.assertNull(found);
    }
/**
 * entity hasn't child*
 * */
    @Test
    void deleteByIdChildfree() {
        if (repository.findById(java.util.UUID.fromString(childfree)) !=null) {
                Assert.assertTrue(repository.deleteById(java.util.UUID.fromString(childfree)));
        }
    }
/**
 * entity has child
 * **/
@Test
void deleteById() {
    if (repository.findById(java.util.UUID.fromString(UUID)) !=null) {
        Assert.assertFalse(repository.deleteById(java.util.UUID.fromString(UUID)));
    }
}

    @Test
    void deleteByIdIncorrect() {
        if (repository.findById(java.util.UUID.fromString(UUID_incorrect)) !=null) {
            Assert.assertFalse(repository.deleteById(java.util.UUID.fromString(UUID_incorrect)));
        }
    }


    @Test
    void findAll() {
        List<SimpleEntity> entityList = repository.findAll();
        Assert.assertEquals(3, entityList.size());
    }

    @Test
    void save() {
        SimpleEntity entity = new SimpleEntity("ivan");
        SimpleEntity entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), entitySaved.getName());
    }
    @Test
  void getChildren(){
    SimpleEntity entity = repository.findById(java.util.UUID.fromString(UUID));
    List<AnotherEntity> anotherEntityList= repository.getChildren(entity.getUuid());
    Assert.assertEquals(anotherEntityList.size(), 2);
    Assert.assertEquals(anotherEntityList.get(0).getName(), "bob");
    Assert.assertEquals(anotherEntityList.get(1).getName(), "tobic");
}

}