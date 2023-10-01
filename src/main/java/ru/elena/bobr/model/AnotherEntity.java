package ru.elena.bobr.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnotherEntity {

    UUID uuid;
    String name;

    UUID simple;
    List<Doctor> doctors;

    public AnotherEntity() {
        this.doctors = new ArrayList<>();
    }

    public AnotherEntity(UUID uuid, String name, UUID simple) {
        this.uuid = uuid;
        this.name = name;
        this.simple = simple;
        this.doctors = new ArrayList<>();
    }

    public AnotherEntity(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.doctors = new ArrayList<>();
    }

    public UUID getSimple() {
        return simple;
    }

    public void setSimple(UUID simple) {
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



    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }


}
