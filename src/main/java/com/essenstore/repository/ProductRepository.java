package com.essenstore.repository;

import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    @EntityGraph(value = "Product.images", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findByCategoryAndGender(Category category, Gender gender, Pageable pageable);

    @Override
    @EntityGraph(value = "Product.csi", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Product> findById(Long id);
}
