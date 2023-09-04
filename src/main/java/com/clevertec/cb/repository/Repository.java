package com.clevertec.cb.repository;

import java.util.List;

public interface Repository<T> {

    void save(T entity);

    void update(T entity);

    void delete(Long id);

    T getById(Long id);

    List<T> getAll();

}
