package com.essenstore.projection;

import com.essenstore.entity.Image;
import com.essenstore.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Transient;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
@Projection(name = "imageProjection", types = Image.class)
public interface ImageProjection {

    Long getId();

    String getName();

    String getPath();

    String getUrl();

}
