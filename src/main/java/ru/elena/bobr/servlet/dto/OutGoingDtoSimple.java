package ru.elena.bobr.servlet.dto;

import java.util.List;
import java.util.UUID;

public class OutGoingDtoSimple {
    private UUID id;
    private String owner;

    private List pets;

    public OutGoingDtoSimple(UUID id, String owner, List pets) {
        this.id = id;
        this.owner = owner;
        this.pets=pets;
    }

    public List getPets() {
        return pets;
    }

    public void setPets(List pets) {
        this.pets = pets;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
