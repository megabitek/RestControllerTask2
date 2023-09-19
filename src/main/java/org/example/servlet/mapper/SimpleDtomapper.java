package org.example.servlet.mapper;

import org.example.model.SimpleEntity;
import org.example.servlet.dto.IncomingDto;
import org.example.servlet.dto.OutGoingDto;

import java.util.List;

public interface SimpleDtomapper {
    SimpleEntity map(IncomingDto incomingDto);

    OutGoingDto map(SimpleEntity simpleEntity);

    List<SimpleEntity> mapListIn(List<IncomingDto> incomingDto);

    List<OutGoingDto> mapListOut(List<SimpleEntity> simpleEntities);
}
