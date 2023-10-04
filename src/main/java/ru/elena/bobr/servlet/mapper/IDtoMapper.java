package ru.elena.bobr.servlet.mapper;

import java.util.List;

public interface IDtoMapper <T, I, O>{
      T mapIn(I incomingDto);

      O mapOut(T entity);

     List<T> mapListIn(List<I> incomingDto);

    List<O> mapListOut(List<T> entities);
}
