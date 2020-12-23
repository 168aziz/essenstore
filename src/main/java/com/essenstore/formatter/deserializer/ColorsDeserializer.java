package com.essenstore.formatter.deserializer;

import com.essenstore.entity.Color;
import com.essenstore.service.ColorService;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ColorsDeserializer extends PropsDeserializer<Color> {

    public ColorsDeserializer(ColorService service) {
        super(service);
    }

}
