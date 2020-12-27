package com.essenstore.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private String description;

    private String name;

    private BigDecimal currentPrice;

    private BigDecimal oldPrice;

    private String gender;

}
