package com.lizavetakeura.api.dao;

import java.util.List;

public interface GenericDao<T> {

    void create(T t);

    void delete(T t);

    T getById(Integer id);

    List<T> getAll();

    void update(T t);
}
