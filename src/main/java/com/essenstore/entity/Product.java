package com.essenstore.entity;

import com.essenstore.validator.Images;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "product")
@ToString(callSuper = true)
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class Product extends BaseEntity {

    @NotBlank
    @Column(name = "description")
    @javax.validation.constraints.Size(min = 8, max = 1800)
    private String description;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @NotNull
    @Column(name = "current_price")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal currentPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors = new HashSet<>();

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private Set<Size> sizes = new HashSet<>();

    @Images
    @NotEmpty
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<com.essenstore.entity.Image> images = new HashSet<>();

    public void setDescription(String description) {
        this.description = StringUtils.normalizeSpace(description);
    }


}
