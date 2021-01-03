package com.essenstore.deserializer;

import com.essenstore.entity.Brand;
import com.essenstore.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class BrandDeserializer extends BaseDeserializer<Brand> {

    @Autowired
    public BrandDeserializer(BrandRepository repository) {
        super(repository);
    }

}
