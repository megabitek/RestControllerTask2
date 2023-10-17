package ru.elena.bobr.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimpleEntity {
    UUID uuid;
    String name;

    List<AnotherEntity> others;

    public SimpleEntity() {
        others = new ArrayList<AnotherEntity>(); 
    }

    public SimpleEntity(String name) {
    uuid = UUID.randomUUID();
    this.name = name;
    }

    public SimpleEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public List<AnotherEntity> getOthers() {
        return others;
    }

    public void setOthers(List<AnotherEntity> others) {
        this.others = others;
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
