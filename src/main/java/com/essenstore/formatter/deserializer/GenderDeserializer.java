package com.essenstore.formatter.deserializer;

import com.essenstore.entity.Gender;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class GenderDeserializer extends JsonDeserializer<Gender> {

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        var gender = Gender.get(jsonParser.getValueAsString());
        if (gender == Gender.EMPTY)
            throw new JsonParseException(jsonParser, "property not valid");
        return gender;
    }
}
