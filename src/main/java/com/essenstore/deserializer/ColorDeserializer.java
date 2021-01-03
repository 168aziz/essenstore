package com.essenstore.deserializer;

import com.essenstore.entity.Color;
import com.essenstore.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ColorDeserializer extends BaseDeserializer<Color> {

    @Autowired
    public ColorDeserializer(ColorRepository repository) {
        super(repository);
    }
}
