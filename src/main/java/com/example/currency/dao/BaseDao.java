package com.example.currency.dao;

import java.util.List;

public interface BaseDao<T> {
    T find(Object Id);

    List<T> list();

    T save(T type);

    T update(T type);

    void delete(T type);

    void deleteById(Object id);

    void flush();

    public void detach(T object);

    T saveOrUpdate(T type);

}
