package org.example.model;

import java.util.List;
import java.util.UUID;

public class SimpleEntity {
    UUID uuid;
    String name;

    List<AnotherEntity> anothers;

    public SimpleEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public List<AnotherEntity> getAnothers() {
        return anothers;
    }

    public void setAnothers(List<AnotherEntity> anothers) {
        this.anothers = anothers;
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
}
