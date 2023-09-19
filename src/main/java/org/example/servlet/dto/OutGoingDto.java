package org.example.servlet.dto;

import java.util.UUID;

public class OutGoingDto {
    private UUID id;
    private String owner;

    public OutGoingDto(UUID id, String owner) {
        this.id = id;
        this.owner = owner;
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
