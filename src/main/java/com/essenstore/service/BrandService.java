package com.essenstore.service;

import com.essenstore.entity.Brand;
import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.exception.NotFoundException;
import com.essenstore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService extends BaseEntityService<Brand, Long> {

    private final BrandRepository repository;

    @Autowired
    public BrandService(Brand emptyObject, BrandRepository repository) {
        super(emptyObject, repository);
        this.repository = repository;
    }

    public List<Brand> getBy(Category category, Gender gender) {
        return repository.findDistinctByCategoryAndGenderOrderByName(category, gender);
    }

}
