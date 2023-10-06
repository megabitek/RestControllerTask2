package ru.elena.bobr.service;

public interface IParentCrudService <C,P >  {


        P addChildEntity( C childEntity, P parentEntity);

        void deleteChildEntity(C children, P parent);
    }


