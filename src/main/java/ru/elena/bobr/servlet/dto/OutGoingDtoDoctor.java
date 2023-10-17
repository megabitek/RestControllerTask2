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

}
