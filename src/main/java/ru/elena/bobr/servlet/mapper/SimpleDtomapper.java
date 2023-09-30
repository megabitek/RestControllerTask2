package ru.elena.bobr.servlet.mapper;

import ru.elena.bobr.model.SimpleEntity;
import ru.elena.bobr.servlet.dto.IncomingDtoSimple;
import ru.elena.bobr.servlet.dto.OutGoingDto;

import java.util.List;

public interface SimpleDtomapper {
    SimpleEntity map(IncomingDtoSimple incomingDtoSimple);

    OutGoingDto map(SimpleEntity simpleEntity);

    List<SimpleEntity> mapListIn(List<IncomingDtoSimple> incomingDtoSimple);

    List<OutGoingDto> mapListOut(List<SimpleEntity> simpleEntities);
}
