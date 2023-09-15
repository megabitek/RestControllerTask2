package org.example.model;

import java.util.List;
import java.util.UUID;

public class SimpleEntity {
    UUID uuid;
    String name;

    public List<AnotherEntyty> getAnothers() {
        return anothers;
    }

    public void setAnothers(List<AnotherEntyty> anothers) {
        this.anothers = anothers;
    }

    List<AnotherEntyty> anothers;

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
