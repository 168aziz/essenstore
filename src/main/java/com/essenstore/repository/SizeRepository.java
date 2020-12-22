package com.essenstore.repository;

import com.essenstore.entity.Product;
import com.essenstore.entity.Size;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends BaseRepository<Size, Long> {

    List<Size> findByProducts(Product product);

}
