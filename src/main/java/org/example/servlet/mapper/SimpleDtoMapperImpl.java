package org.example.servlet.mapper;

import org.example.model.SimpleEntity;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;

import java.util.ArrayList;
import java.util.List;

public class SimpleDtoMapperImpl implements SimpleDtomapper {
    @Override
    public SimpleEntity map(IncomingDto incomingDto) {
return new SimpleEntity (incomingDto.getId(), incomingDto.getOwner());

    }

    @Override
    public OutGoingDto map(SimpleEntity simpleEntity) {
        return new OutGoingDto(simpleEntity.getUuid(), simpleEntity.getName());
    }

    public List<SimpleEntity> mapListIn(List<IncomingDto> incomingDto) {
        List<SimpleEntity> entityList = new ArrayList<SimpleEntity>(incomingDto.size());
        for (int i=0; i<incomingDto.size(); i++) {
            entityList.add(new SimpleEntity(incomingDto.get(i).getId(), incomingDto.get(i).getOwner()));
        }
     return  entityList;
    }

    public List<OutGoingDto> mapListOut(List<SimpleEntity> simpleEntities) {
        List<OutGoingDto> outGoingDtoList = new ArrayList<OutGoingDto>(simpleEntities.size());
        for (int i=0; i<simpleEntities.size(); i++) {
            outGoingDtoList.add(new OutGoingDto(simpleEntities.get(i).getUuid(), simpleEntities.get(i).getName()));
        }
        return  outGoingDtoList;
    }
}
