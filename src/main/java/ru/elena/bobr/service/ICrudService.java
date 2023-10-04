package ru.elena.bobr.service;

import java.util.List;
import java.util.Optional;

public interface ICrudService<K, T> {


    T save(T entity);

    T findById(K uuid);

    Optional<T> delete (K uuid);

    List<T> findAll();

    T update(T entity);
}
