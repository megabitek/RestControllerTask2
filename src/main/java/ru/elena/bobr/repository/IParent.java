package ru.elena.bobr.repository;

import java.util.List;
import java.util.UUID;

public interface IParent<C >{

    List<C> getChildren(UUID parent_uuid);
    boolean deleteAllChildren(UUID parent_uuid );



}
