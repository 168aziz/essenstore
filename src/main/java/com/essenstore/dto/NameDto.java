package com.essenstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameDto {

    @JsonProperty("name")
    private String name;

}
