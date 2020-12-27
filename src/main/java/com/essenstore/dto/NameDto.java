package com.essenstore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NameDto {

    @Size(max = 250, message = "Property size limit with 250 symbols")
    @NotBlank(message = "Property name is empty")
    private String name;

}
