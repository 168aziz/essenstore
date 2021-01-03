package com.essenstore.repository;

import com.essenstore.entity.Image;
import com.essenstore.projection.ImageProjection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(excerptProjection = ImageProjection.class)
public interface ImageRepository extends TransformRepository<Image, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Image> S save(S s);

}
