package com.essenstore.formatter.deserializer;

import com.essenstore.entity.BaseEntity;
import com.essenstore.service.BaseEntityService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor
public abstract class PropsDeserializer<T extends BaseEntity> extends JsonDeserializer<T> {

    private final BaseEntityService<T, Long> service;

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        var id = jsonParser.getValueAsString().trim();
        if (StringUtils.isNumeric(id)) {
            return service.getBy(Long.parseLong(id));
        } else {
            throw new JsonParseException(jsonParser, "property not valid");
        }
    }
}
