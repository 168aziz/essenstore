package com.essenstore.projection;

import com.essenstore.entity.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.rest.core.config.Projection;

@JsonNaming(KebabCaseStrategy.class)
@Projection(name = "basicInfo", types = {Category.class,
        Brand.class, Size.class, Color.class, Product.class})
public interface NameProjection {

    Long getId();

    String getName();

}
