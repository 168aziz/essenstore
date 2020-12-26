package com.essenstore.service;

import com.essenstore.entity.BaseEntity;
import com.essenstore.exception.NotFoundException;
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
    public List<T> getAll() throws NotFoundException {
        var list = repository.findAll(Sort.by(sortBy).ascending());
        if (list.isEmpty())
            throw new NotFoundException();
        return list;
    }

    @Override
    public Page<T> getAll(Pageable pageable) throws NotFoundException {
        var page = repository.findAll(pageable);
        if (!page.hasContent())
            throw new NotFoundException();
        return page;
    }

    @Override
    public T getBy(ID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException();
        });
    }

    @Override
    public T getBy(String name) throws NotFoundException {
        return repository.findByNameIgnoreCase(name).orElseThrow(() -> {
            throw new NotFoundException();
        });
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
    public void delete(ID id) throws NotFoundException {
        repository.findById(id)
                .ifPresentOrElse(repository::delete, () -> {
                    throw new NotFoundException();
                });
    }

    @Override
    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean exists(String name) {
        return repository.existsByName(name);
    }

    @Transactional
    public T update(ID id, T newEntity) throws NotFoundException {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(newEntity.getName());
                    return repository.save(entity);
                }).orElseThrow(() -> {
                    throw new NotFoundException();
                });
    }

}
