package ru.elena.bobr.repository;



import java.sql.SQLException;
import java.util.List;

public interface EntityRepository<T, K> {


    T findById(K id);

    boolean deleteById(K id) ;

    List<T> findAll();

    T save(T t);

    T update(T t) throws SQLException;

}
