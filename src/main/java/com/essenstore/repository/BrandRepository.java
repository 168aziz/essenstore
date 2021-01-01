package com.essenstore.repository;

import com.essenstore.entity.Brand;
import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.projection.NameProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = NameProjection.class)
public interface BrandRepository extends BaseRepository<Brand, Long> {

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.category=:category AND p.gender=:gender ORDER BY  p.brand.name")
    List<Brand> findDistinctByCategoryAndGenderOrderByName(@Param("category") Category category, @Param("gender") Gender gender);

}
