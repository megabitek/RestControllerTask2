package org.example.servlet.mapper;

import org.example.model.SimpleEntity;
import org.example.servlet.dto.IncomingDtoSimple;
import org.example.servlet.dto.OutGoingDto;

import java.util.ArrayList;
import java.util.List;

public class SimpleDtoMapperImpl implements SimpleDtomapper {
    @Override
    public SimpleEntity map(IncomingDtoSimple incomingDtoSimple) {
return new SimpleEntity (incomingDtoSimple.getId(), incomingDtoSimple.getOwner());

    }

    @Override
    public OutGoingDto map(SimpleEntity simpleEntity) {
        return new OutGoingDto(simpleEntity.getUuid(), simpleEntity.getName(), simpleEntity.getOthers());
    }

    public List<SimpleEntity> mapListIn(List<IncomingDtoSimple> incomingDtoSimple) {
        List<SimpleEntity> entityList = new ArrayList<SimpleEntity>(incomingDtoSimple.size());
        for (int i = 0; i< incomingDtoSimple.size(); i++) {
            entityList.add(new SimpleEntity(incomingDtoSimple.get(i).getId(), incomingDtoSimple.get(i).getOwner()));
        }
     return  entityList;
    }

    public List<OutGoingDto> mapListOut(List<SimpleEntity> simpleEntities) {
        List<OutGoingDto> outGoingDtoList = new ArrayList<OutGoingDto>(simpleEntities.size());
        for (int i=0; i<simpleEntities.size(); i++) {
            outGoingDtoList.add(new OutGoingDto(simpleEntities.get(i).getUuid(), simpleEntities.get(i).getName(), simpleEntities.get(i).getOthers()));
        }
        return  outGoingDtoList;
    }
}
