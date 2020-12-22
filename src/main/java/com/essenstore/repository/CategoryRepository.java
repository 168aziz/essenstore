package com.essenstore.repository;

import com.essenstore.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    @EntityGraph(value = "Category.products", type = EntityGraphType.LOAD)
    Optional<Category> findWithProductsByNameIgnoreCase(String name);

}
