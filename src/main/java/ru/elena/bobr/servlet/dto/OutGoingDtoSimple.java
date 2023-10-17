package ru.elena.bobr.servlet.dto;

import ru.elena.bobr.model.AnotherEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OutGoingDtoSimple {
    private UUID id;
    private String owner;

    private List pets= new ArrayList<AnotherEntity>();

    public OutGoingDtoSimple(UUID id, String owner, List pets) {
        this.id = id;
        this.owner = owner;
        this.pets=pets;
    }


}
