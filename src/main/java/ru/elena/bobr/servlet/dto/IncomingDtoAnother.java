package ru.elena.bobr.servlet.dto;

import java.util.UUID;

public class IncomingDtoAnother {
    UUID id;
    String nickname;

    UUID ownerId;

    public IncomingDtoAnother(UUID id, String nickname, UUID ownerId) {
        this.id = id;
        this.nickname = nickname;
        this.ownerId = ownerId;
    }

    public UUID getOwner() {
        return ownerId;
    }

    public void setOwner(String owner) {
        this.ownerId = ownerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNikname(String nickname) {
        this.nickname = nickname;
    }
}


