package com.essenstore.service;

import com.essenstore.entity.BaseEntity;
import com.essenstore.repository.BaseRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
public abstract class BaseEntityService<T extends BaseEntity, ID> implements EntityService<T, ID>, FieldUniqueService {

    private String sortBy = "id";

    @Getter
    private final T emptyObject;

    private final BaseRepository<T, ID> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll(Sort.by(sortBy).ascending());
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T getBy(ID id) {
        return repository.findById(id).orElse(emptyObject);
    }

    @Override
    public T getBy(String name) {
        return repository.findByNameIgnoreCase(name).orElse(emptyObject);
    }

    @Override
    @Transactional
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean exists(String name) {
        return repository.existsByName(name);
    }


}
