package com.essenstore.repository;

import com.essenstore.entity.Color;
import com.essenstore.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends BaseRepository<Color, Long> {

    List<Color> findByProducts(Product product);
}
