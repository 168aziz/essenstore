package com.essenstore.projection;

import com.essenstore.entity.Image;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
@Projection(name = "imageProjection", types = Image.class)
public interface ImageProjection {

    Long getId();

    @Value("#{target.url + target.path +  target.name}")
    String getEndpoint();

}
