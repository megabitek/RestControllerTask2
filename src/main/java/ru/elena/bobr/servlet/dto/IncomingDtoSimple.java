package ru.elena.bobr.servlet.dto;

import java.util.UUID;

public class IncomingDtoSimple {
UUID id;
String ownername;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnerName(String owner) {
        this.ownername = ownername;
    }
}
