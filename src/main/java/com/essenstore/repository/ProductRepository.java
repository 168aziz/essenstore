package com.essenstore.repository;

import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import com.essenstore.projection.BasicProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = BasicProductProjection.class)
public interface ProductRepository extends TransformRepository<Product, Long> {

    Page<Product> findByCategoryAndGender(Category category, Gender gender, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Product> S save(S s);

}
