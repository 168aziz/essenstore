package com.essenstore.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.access.prepost.PreAuthorize;

@NoRepositoryBean
public interface TransformRepository<T, ID> extends BaseRepository<T, ID> {

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    <S extends T> S save(S s);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    void delete(T t);

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    void deleteById(ID id);
    
}
