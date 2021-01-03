package com.essenstore.deserializer;

import com.essenstore.entity.Size;
import com.essenstore.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class SizeDeserializer extends BaseDeserializer<Size> {

    @Autowired
    public SizeDeserializer(SizeRepository repository) {
        super(repository);
    }

}
