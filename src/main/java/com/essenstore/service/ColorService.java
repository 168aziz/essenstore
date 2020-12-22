package com.essenstore.service;

import com.essenstore.entity.Color;
import com.essenstore.entity.Product;
import com.essenstore.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService extends BaseEntityService<Color, Long> {

    private final ColorRepository repository;

    @Autowired
    public ColorService(Color emptyObject, ColorRepository repository) {
        super(emptyObject, repository);
        this.repository = repository;
    }

    public List<Color> getBy(Product product) {
        return repository.findByProducts(product);
    }

}
