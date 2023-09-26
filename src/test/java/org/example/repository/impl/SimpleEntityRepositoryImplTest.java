package org.example.repository.impl;

import org.example.db.PostrgreSQLConnection;
import org.example.model.SimpleEntity;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


class SimpleEntityRepositoryImplTest {

    public static final String UUID = "76bde8dd-f961-4653-85f5-bcdc4ac171f0";
    @Mock
    SimpleEntityRepositoryImpl repository= new SimpleEntityRepositoryImpl();



    @Container
    @InjectMocks
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16").withDatabaseName("mydatabase")
                    .withUsername("postgres")
                    .withInitScript("db-migration.SQL")
                    .withPassword("1234");

    @BeforeEach
    void setUp() {
         Connection connection = new PostrgreSQLConnection().getConnection
                (postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
     repository.connection =connection;
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
        SimpleEntity found = repository.findById(java.util.UUID.fromString(UUID));
        Assert.assertNotNull(found);
    }



    @Test
    void deleteById() {
        if (repository.findById(java.util.UUID.fromString(UUID)) !=null)
        Assert.assertTrue(repository.deleteById(java.util.UUID.fromString(UUID)));
    }

    @Test
    void findAll() {
        List<SimpleEntity> entityList = repository.findAll();
        Assert.assertEquals(1, entityList.size());
    }

    @Test
    void save() {
        SimpleEntity entity = new SimpleEntity("ivanov");
        SimpleEntity entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), entitySaved.getName());
    }


}