package ru.elena.bobr.model;

import java.util.List;
import java.util.UUID;

public class Doctor {
    String name;
    UUID uuid;
    String lastName;
    List<AnotherEntity> pets;

    public Doctor() {
    }

    public Doctor(UUID uuid, String name, String lastName) {
        this.name = name;
        this.uuid = uuid;
        this.lastName = lastName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AnotherEntity> getPets() {
        return pets;
    }

    public void setPets(List<AnotherEntity> pets) {
        this.pets = pets;
    }
}
