package ru.elena.bobr.servlet.dto;

import java.util.List;
import java.util.UUID;

public class OutGoingDtoAnother {
    private UUID id;
    private String nickname;
    //private UUID ownerId;
    private List doctors;

    public OutGoingDtoAnother(UUID id, String nickname, UUID ownerId, List doctors) {
        this.id = id;
        this.nickname = nickname;
        //this.ownerId = ownerId;
        this.doctors = doctors;
    }

    public OutGoingDtoAnother(UUID id, String nickname, UUID ownerId) {
        this.id = id;
        this.nickname = nickname;
        this.doctors=doctors;
    }

    public List getPets() {
        return doctors;
    }

    public void setdoctors(List doctors) {
        this.doctors = doctors;
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}


