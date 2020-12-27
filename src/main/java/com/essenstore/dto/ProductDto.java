package com.essenstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal currentPrice;

    private BigDecimal oldPrice;

    @JsonProperty("brand-name")
    private String brandName;

}
