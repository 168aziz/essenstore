package com.essenstore.dto;

import com.essenstore.entity.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal oldPrice;

    private BigDecimal currentPrice;

    private String brand;

    private String category;

    private Gender gender;

    private Set<String> colors = new HashSet<>();

    private Set<String> sizes = new HashSet<>();

    private Set<String> images = new HashSet<>();

}
