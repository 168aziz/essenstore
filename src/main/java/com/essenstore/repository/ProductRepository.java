package com.essenstore.repository;

import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import com.essenstore.projection.BasicProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = BasicProductProjection.class)
public interface ProductRepository extends BaseRepository<Product, Long> {

    @EntityGraph(value = "Product.images", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findByCategoryAndGender(Category category, Gender gender, Pageable pageable);

}
