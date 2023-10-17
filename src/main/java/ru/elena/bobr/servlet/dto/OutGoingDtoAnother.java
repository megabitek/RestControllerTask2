package ru.elena.bobr.servlet.dto;

import ru.elena.bobr.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OutGoingDtoAnother {
    private UUID id;
    private String nickname;
    //private UUID ownerId;
    private List doctors =  new ArrayList<Doctor>();

    public OutGoingDtoAnother(UUID id, String nickname, UUID ownerId, List doctors) {
        this.id = id;
        this.nickname = nickname;
        //this.ownerId = ownerId;
        this.doctors = doctors;
    }





}


