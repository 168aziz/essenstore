package com.essenstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntityService<T, ID> {

    List<T> getAll();

    Page<T> getAll(Pageable pageable);

    T getBy(ID id);

    T getBy(String name);

    T save(T t);

    T update(T t);

    void delete(ID id);

    boolean exists(ID id);

}
