package com.essenstore.formatter.deserializer;


import com.essenstore.entity.Size;
import com.essenstore.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;


@JsonComponent
public class SizeDeserializer extends PropsDeserializer<Size> {

    @Autowired
    public SizeDeserializer(SizeService service) {
        super(service);
    }

}
