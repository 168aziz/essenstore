package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "product")
@ToString(callSuper = true)
@NamedEntityGraph(name = "Product.csi",
        attributeNodes = {@NamedAttributeNode("colors"), @NamedAttributeNode("sizes"), @NamedAttributeNode("images")})
@NamedEntityGraph(name = "Product.images", attributeNodes = @NamedAttributeNode("images"))
public class Product extends BaseEntity {

    @Column(name = "description")
    private String description;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Column(name = "old_price")
    private BigDecimal oldPrice;

    @Column(name = "current_price")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal currentPrice;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonProperty(value = "brand-id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "gender")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JoinTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    private Set<Color> colors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private Set<Size> sizes = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images = new HashSet<>();

    public void setDescription(String description) {
        this.description = StringUtils.normalizeSpace(description);
    }

    public void setColors(Collection<Color> colors) {
        this.colors = new HashSet<>(colors);
    }

    public void setSizes(Collection<Size> sizes) {
        this.sizes = new HashSet<>(sizes);
    }

}
