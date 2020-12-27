package com.essenstore.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PropDetailDto {

    private String name;

    private String createdAt;

    private Set<String> products;

}
