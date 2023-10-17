package ru.elena.bobr.repository.impl;

import ru.elena.bobr.db.ConnectionFactory;
import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;


class SimpleEntityRepositoryImplTest {

    public static final String UUIDCorrect = "76bde8dd-f961-4653-85f5-bcdc4ac171f0";
    public static final String childfree = "11111111-6666-3333-4444-555555555555";

    public static  final String childWithRelationship = "11111111-2222-3333-4444-555555555555";
    public static final String UUIDIncorrect = "89898989-f961-4653-85f5-bcdc4ac171f0";
    private static  final String nameSaved = "ivan";

    private static  final String nameUpdated = "dima";
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
         SimpleEntity found = repository.findById(UUID.fromString(UUIDCorrect));
        Assert.assertNotNull(found);
    }

    @Test
    void findByWrongId() {
        SimpleEntity found = repository.findById(UUID.fromString(UUIDIncorrect));
        Assert.assertNull(found);
    }
/**
 * entity hasn't child*
 * */
    @Test
    void deleteByIdChildfree() {
        if (repository.findById(UUID.fromString(childfree)) !=null) {
                Assert.assertTrue(repository.deleteById(java.util.UUID.fromString(childfree)));
        }
    }
/**
 * entity has child
 * **/
@Test
void deleteById() {
    if (repository.findById(UUID.fromString(UUIDCorrect)) !=null) {
        Assert.assertFalse(repository.deleteById(java.util.UUID.fromString(UUIDCorrect)));
    }
}

    @Test
    void deleteByIdIncorrect() {
        if (repository.findById(UUID.fromString(UUIDIncorrect)) !=null) {
            Assert.assertFalse(repository.deleteById(java.util.UUID.fromString(UUIDIncorrect)));
        }
    }


    @Test
    void findAll() {
        List<SimpleEntity> entityList = repository.findAll();
        Assert.assertEquals(3, entityList.size());
    }

    @Test
    void save() {
        SimpleEntity entity = new SimpleEntity(nameSaved);
        SimpleEntity entitySaved = repository.save(entity);
        Assert.assertEquals(entity.getUuid(), entitySaved.getUuid());
        Assert.assertEquals(entity.getName(), entitySaved.getName());
    }

    @Test
    void update(){
        SimpleEntity entity= repository.findById(UUID.fromString(UUIDCorrect));
        entity.setName(nameUpdated );
        SimpleEntity updated = repository.update(entity);
        Assert.assertNotNull(updated);
        Assert.assertEquals(entity.getName(), updated.getName());
        Assert.assertEquals(entity.getUuid(), updated.getUuid());
        Assert.assertEquals(entity.getOthers(), updated.getOthers());

    }
    @Test
  void getChildren(){
    SimpleEntity entity = repository.findById(UUID.fromString(UUIDCorrect));
    List<AnotherEntity> anotherEntityList= repository.getChildren(entity.getUuid());
    Assert.assertEquals(anotherEntityList.size(), 2);
    Assert.assertEquals(anotherEntityList.get(0).getName(), "bob");
    Assert.assertEquals(anotherEntityList.get(1).getName(), "tobic");
}
@Test
void deleteChildrenWithoutRelation(){
    Assert.assertFalse(repository.deleteAllChildren(UUID.fromString(childWithRelationship)));
}
}