package ru.elena.bobr.service;

import java.util.List;

public interface ICrudService<K, T> {


    T save(T entity);

    T findById(K uuid);

    T delete (K uuid);

    List<T> findAll();

    T update(T entity);
}
