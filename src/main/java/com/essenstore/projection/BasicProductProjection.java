package com.essenstore.projection;

import com.essenstore.entity.Product;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;


@JsonNaming(KebabCaseStrategy.class)
@Projection(name = "basicProductProjection", types = Product.class)
public interface BasicProductProjection {

    Long getId();

    String getName();

    BigDecimal getOldPrice();

    BigDecimal getCurrentPrice();

    NameProjection getBrand();

}
