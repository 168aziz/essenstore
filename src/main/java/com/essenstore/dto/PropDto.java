package com.essenstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PropDto {

    private Long id;

    @Size(max = 250, message = "Property size limit with 250 symbols")
    @NotBlank(message = "Property name is empty")
    private String name;

}
