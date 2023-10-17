package ru.elena.bobr.service;

import ru.elena.bobr.model.AnotherEntity;
import ru.elena.bobr.model.Doctor;

public interface IParentCrudService <C,P >  {


        P addChildEntity( C childEntity, P parentEntity);



        void deleteAllChildren( P parent);


}


