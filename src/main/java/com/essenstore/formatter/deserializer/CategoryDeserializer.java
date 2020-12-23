package com.essenstore.formatter.deserializer;

import com.essenstore.entity.Category;
import com.essenstore.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CategoryDeserializer extends PropsDeserializer<Category> {

    @Autowired
    public CategoryDeserializer(BaseEntityService<Category, Long> service) {
        super(service);
    }

}
