package org.example.model;

import java.util.UUID;

public class AnotherEntity {

    UUID uuid;
    String name;
    UUID simple;

    public AnotherEntity(UUID uuid, String name, UUID simple) {
        this.uuid = uuid;
        this.name = name;
        this.simple = simple;
    }
}
