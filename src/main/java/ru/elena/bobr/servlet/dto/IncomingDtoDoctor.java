package ru.elena.bobr.servlet.dto;

import ru.elena.bobr.model.AnotherEntity;

import java.util.List;
import java.util.UUID;

public class IncomingDtoDoctor {
    UUID id;
    String name;
    String lastname;

    public IncomingDtoDoctor() {
    }

    public IncomingDtoDoctor(UUID id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
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
}
