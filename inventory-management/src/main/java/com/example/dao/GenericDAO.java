package com.example.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    void save(T entity);
    void update(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(ID id);
    void deleteEntity(T entity);
}