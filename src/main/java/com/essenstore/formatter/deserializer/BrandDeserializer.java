package com.essenstore.formatter.deserializer;

import com.essenstore.entity.Brand;
import com.essenstore.service.BaseEntityService;
import com.essenstore.service.BrandService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class BrandDeserializer extends PropsDeserializer<Brand> {

    @Autowired
    public BrandDeserializer(BrandService service) {
        super(service);
    }
}
