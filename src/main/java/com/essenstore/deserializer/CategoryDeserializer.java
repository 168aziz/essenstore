package com.essenstore.deserializer;

import com.essenstore.entity.Category;
import com.essenstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CategoryDeserializer extends BaseDeserializer<Category> {

    @Autowired
    public CategoryDeserializer(CategoryRepository repository) {
        super(repository);
    }
}
