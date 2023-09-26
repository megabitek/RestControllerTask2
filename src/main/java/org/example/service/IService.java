package org.example.service;

import org.example.model.SimpleEntity;

import java.util.List;
import java.util.UUID;

public interface IService<K, T> {


    T save(T entity);

    T findById(K uuid);

    T delete (K uuid);

    List<T> findAll();

    T update(T entity);
}
