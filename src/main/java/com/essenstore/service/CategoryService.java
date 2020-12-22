package com.essenstore.service;

import com.essenstore.entity.Category;
import com.essenstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseEntityService<Category, Long> {

    @Autowired
    public CategoryService(Category emptyCategory, CategoryRepository repository) {
        super(emptyCategory, repository);
    }

}
