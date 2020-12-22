package com.essenstore.service;

import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import com.essenstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseEntityService<Product, Long> {

    private final ProductRepository repository;

    private final CategoryService categoryService;

    @Autowired
    public ProductService(Product emptyObject, CategoryService categoryService, ProductRepository repository) {
        super(emptyObject, repository);
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Page<Product> getBy(String category, String gender, Pageable pageable) {
        Gender genderVal = Gender.get(gender);
        System.out.println(genderVal);
        Category categoryVal = categoryService.getBy(category);
        System.out.println(categoryVal);
        if (genderVal == Gender.EMPTY || !categoryVal.isExist())
            return Page.empty();
        return repository.findByCategoryAndGender(categoryVal, genderVal, pageable);
    }

}
