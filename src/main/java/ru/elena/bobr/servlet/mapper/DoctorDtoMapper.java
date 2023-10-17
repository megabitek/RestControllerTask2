package ru.elena.bobr.servlet.mapper;

import ru.elena.bobr.model.Doctor;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.servlet.dto.IncomingDtoDoctor;
import ru.elena.bobr.servlet.dto.OutGoingDtoDoctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDtoMapper implements IDtoMapper<Doctor, IncomingDtoDoctor, OutGoingDtoDoctor>{
    @Override
    public Doctor mapIn(IncomingDtoDoctor incomingDto) {
        return  new Doctor(incomingDto.getId(), incomingDto.getName(), incomingDto.getLastname());
    }

    @Override
    public OutGoingDtoDoctor mapOut(Doctor entity) {
        return new OutGoingDtoDoctor(entity.getUuid(), entity.getName(), entity.getLastName(), entity.getPets());
    }

 /*   @Override
    public List<Doctor> mapListIn(List<IncomingDtoDoctor> incomingDto) {
        List<Doctor> entityList = new ArrayList<Doctor>(incomingDto.size());
        incomingDto.stream().forEach(in->entityList.add(new Doctor(in.getId(), in.getName(), in.getLastname())));
        return entityList;
    }*/

    @Override
    public List<OutGoingDtoDoctor> mapListOut(List<Doctor> entities) {
        List<OutGoingDtoDoctor> entityList = new ArrayList<OutGoingDtoDoctor>(entities.size());
        entities.stream().forEach(doctor->entityList.add(new OutGoingDtoDoctor(doctor.getUuid(), doctor.getName(), doctor.getLastName(), doctor.getPets())));
        return entityList;
    }
}
