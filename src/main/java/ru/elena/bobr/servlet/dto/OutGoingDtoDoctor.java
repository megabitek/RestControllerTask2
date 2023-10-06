package ru.elena.bobr.servlet.dto;

import ru.elena.bobr.model.AnotherEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OutGoingDtoDoctor {
    UUID id;
    String name;
    String lastname;
    List<AnotherEntity> patients = new ArrayList<>();

    public OutGoingDtoDoctor(UUID id, String name, String lastname, List patients) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.patients =patients;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<AnotherEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<AnotherEntity> patients) {
        this.patients = patients;
    }
}
