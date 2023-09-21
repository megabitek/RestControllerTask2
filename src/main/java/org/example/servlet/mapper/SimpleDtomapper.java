package org.example.servlet.mapper;

import org.example.model.SimpleEntity;
import org.example.servlet.dto.IncomingDtoSimple;
import org.example.servlet.dto.OutGoingDto;

import java.util.List;

public interface SimpleDtomapper {
    SimpleEntity map(IncomingDtoSimple incomingDtoSimple);

    OutGoingDto map(SimpleEntity simpleEntity);

    List<SimpleEntity> mapListIn(List<IncomingDtoSimple> incomingDtoSimple);

    List<OutGoingDto> mapListOut(List<SimpleEntity> simpleEntities);
}
