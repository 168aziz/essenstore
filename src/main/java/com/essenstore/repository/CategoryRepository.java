package com.essenstore.repository;

import com.essenstore.entity.Category;
import com.essenstore.projection.NameProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = NameProjection.class)
public interface CategoryRepository extends BaseRepository<Category, Long> {

    @EntityGraph(value = "Category.products", type = EntityGraphType.LOAD)
    Optional<Category> findWithProductsByNameIgnoreCase(String name);

}
