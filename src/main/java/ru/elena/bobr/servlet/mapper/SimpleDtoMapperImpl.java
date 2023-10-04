package ru.elena.bobr.servlet.mapper;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.servlet.dto.IncomingDtoSimple;
import ru.elena.bobr.servlet.dto.OutGoingDtoSimple;

import java.util.ArrayList;
import java.util.List;

public class SimpleDtoMapperImpl implements IDtoMapper<SimpleEntity, IncomingDtoSimple, OutGoingDtoSimple> {


    @Override
    public SimpleEntity mapIn(IncomingDtoSimple incomingDtoSimple) {
return new SimpleEntity (incomingDtoSimple.getId(), incomingDtoSimple.getOwner());
    }


    @Override
    public OutGoingDtoSimple mapOut(SimpleEntity simpleEntity) {
        return new OutGoingDtoSimple(simpleEntity.getUuid(), simpleEntity.getName(), simpleEntity.getOthers());
    }


    public List<SimpleEntity> mapListIn(List<IncomingDtoSimple> incomingDtoSimple) {
        List<SimpleEntity> entityList = new ArrayList<SimpleEntity>(incomingDtoSimple.size());
        for (int i = 0; i< incomingDtoSimple.size(); i++) {
            entityList.add(new SimpleEntity(incomingDtoSimple.get(i).getId(), incomingDtoSimple.get(i).getOwner()));
        }
     return  entityList;
    }

    public List<OutGoingDtoSimple> mapListOut(List<SimpleEntity> simpleEntities) {
        List<OutGoingDtoSimple> outGoingDtoList = new ArrayList<OutGoingDtoSimple>(simpleEntities.size());
        for (int i=0; i<simpleEntities.size(); i++) {
            outGoingDtoList.add(new OutGoingDtoSimple(simpleEntities.get(i).getUuid(), simpleEntities.get(i).getName(), simpleEntities.get(i).getOthers()));
        }
        return  outGoingDtoList;
    }
}
