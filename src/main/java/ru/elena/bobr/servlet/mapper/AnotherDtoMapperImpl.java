package ru.elena.bobr.servlet.mapper;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.servlet.dto.IncomingDtoAnother;
import ru.elena.bobr.servlet.dto.OutGoingDtoAnother;
import ru.elena.bobr.servlet.dto.OutGoingDtoSimple;

import java.util.ArrayList;
import java.util.List;

public class AnotherDtoMapperImpl implements IDtoMapper<AnotherEntity, IncomingDtoAnother, OutGoingDtoAnother> {
    @Override
    public AnotherEntity mapIn(IncomingDtoAnother incomingDto) {
        return new AnotherEntity(incomingDto.getId(), incomingDto.getNickname());
    }

    @Override
    public OutGoingDtoAnother mapOut(AnotherEntity entity) {
        return new OutGoingDtoAnother(entity.getUuid(), entity.getName(), entity.getSimple(), entity.getDoctors());
    }

    @Override
    public List<AnotherEntity> mapListIn(List<IncomingDtoAnother> incomingDto) {
        List<AnotherEntity> entityList = new ArrayList<AnotherEntity>(incomingDto.size());
        for (int i = 0; i< incomingDto.size(); i++) {
            entityList.add(new AnotherEntity(incomingDto.get(i).getId(), incomingDto.get(i).getNickname(),
                    incomingDto.get(i).getOwner()));
        }
        return  entityList;
    }

    @Override
    public List<OutGoingDtoAnother> mapListOut(List<AnotherEntity> entities) {
        List<OutGoingDtoAnother> outGoingDtoList = new ArrayList<OutGoingDtoAnother>(entities.size());
        for (int i=0; i<entities.size(); i++) {
            outGoingDtoList.add(new OutGoingDtoAnother(entities.get(i).getUuid(), entities.get(i).getName(), entities.get(i).getSimple(), entities.get(i).getDoctors()));
        }
        return  outGoingDtoList;    }
}
