package com.essenstore.repository;

import com.essenstore.entity.Color;
import com.essenstore.entity.Product;
import com.essenstore.projection.NameProjection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = NameProjection.class)
public interface ColorRepository extends TransformRepository<Color, Long> {

    List<Color> findByProducts(Product product);

}
