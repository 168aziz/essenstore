package com.essenstore.service;

import com.essenstore.entity.Product;
import com.essenstore.entity.Size;
import com.essenstore.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService extends BaseEntityService<Size, Long> {

    private final SizeRepository repository;

    @Autowired
    public SizeService(Size emptyObject, SizeRepository repository) {
        super(emptyObject, repository);
        this.repository = repository;
    }

    public List<Size> getBy(Product product) {
        return repository.findByProducts(product);
    }
}
