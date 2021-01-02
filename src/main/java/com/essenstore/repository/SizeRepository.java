package com.essenstore.repository;

import com.essenstore.entity.Product;
import com.essenstore.entity.Size;
import com.essenstore.projection.NameProjection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = NameProjection.class)
public interface SizeRepository extends TransformRepository<Size, Long> {

    List<Size> findByProducts(Product product);

}
