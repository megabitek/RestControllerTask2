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



    public UUID getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }


}
