package com.essenstore.repository;

import com.essenstore.entity.Brand;
import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Long> {

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.category=:category AND p.gender=:gender ORDER BY  p.brand.name")
    List<Brand> findDistinctByCategoryAndGender(@Param("category") Category category, @Param("gender") Gender gender);

}
