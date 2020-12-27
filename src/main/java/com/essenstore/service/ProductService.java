package com.essenstore.service;

import com.essenstore.entity.Category;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import com.essenstore.exception.NotFoundException;
import com.essenstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.essenstore.utils.Utils.updateProduct;

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

    public Page<Product> getBy(String category, String gender, Pageable pageable) throws NotFoundException {
        Gender genderVal = Gender.get(gender);
        Category categoryVal = categoryService.getBy(category);
        if (genderVal == Gender.EMPTY)
            throw new NotFoundException();
        var page = repository.findByCategoryAndGender(categoryVal, genderVal, pageable);

        if (!page.hasContent())
            throw new NotFoundException();

        return page;
    }

    @Override
    @Transactional
    public Product update(Long id, Product product) throws NotFoundException {
        return repository.findById(id)
                .map(currentProduct -> {
                    updateProduct(currentProduct, product);
                    return repository.save(currentProduct);
                }).orElseThrow(() -> {
                    throw new NotFoundException();
                });
    }

}
