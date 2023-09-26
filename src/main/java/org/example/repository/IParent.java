package org.example.repository;

import org.example.model.AnotherEntity;

import java.util.List;
import java.util.UUID;

public interface IParent<P, C>{



    List<AnotherEntity> getChildren(UUID simple_uuid);
}
