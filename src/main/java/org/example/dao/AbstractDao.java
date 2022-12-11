package org.example.dao;

import org.example.entities.AbstractEntity;

import java.util.List;

public interface AbstractDao<T extends AbstractEntity> {
    T getById(Long id);

    List<T> getAll();

    void deleteById(Long id);

    void update(T entity);

    T create(T entity);
}