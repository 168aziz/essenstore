package com.essenstore.deserializer;

import com.essenstore.entity.BaseEntity;
import com.essenstore.repository.BaseRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class BaseDeserializer<T extends BaseEntity> extends JsonDeserializer<T> {

    private final BaseRepository<T, Long> repository;


    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (StringUtils.isNumeric(jsonParser.getValueAsString()))
            return repository.findById(jsonParser.getLongValue()).orElse(null);
        return null;
    }

}
