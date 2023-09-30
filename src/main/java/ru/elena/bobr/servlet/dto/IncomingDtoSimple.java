package ru.elena.bobr.servlet.dto;

import java.util.UUID;

public class IncomingDtoSimple {
UUID id;
String owner;

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
