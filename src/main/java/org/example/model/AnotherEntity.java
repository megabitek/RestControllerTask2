package org.example.model;

import java.util.UUID;

public class AnotherEntity {

    UUID uuid;
    String name;
    UUID simple;

    public AnotherEntity() {
    }



    public AnotherEntity(UUID uuid, String name, UUID simple) {
        this.uuid = uuid;
        this.name = name;
        this.simple = simple;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getSimple() {
        return simple;
    }

    public void setSimple(UUID simple) {
        this.simple = simple;
    }
}
