package com.essenstore.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
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
@Accessors(chain = true)
@ToString(of = {"name"}, callSuper = true)
@NamedEntityGraph(name = "Product.csi",
        attributeNodes = {@NamedAttributeNode("colors"), @NamedAttributeNode("sizes"), @NamedAttributeNode("images")})
@NamedEntityGraph(name = "Product.images", attributeNodes = @NamedAttributeNode("images"))
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

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

    public Product setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
        return this;
    }

    public Product setDescription(String description) {
        this.description = StringUtils.normalizeSpace(description);
        return this;
    }

    public Product setColors(Collection<Color> colors) {
        this.colors = new HashSet<>(colors);
        return this;
    }

    public Product setSizes(Collection<Size> sizes) {
        this.sizes = new HashSet<>(sizes);
        return this;
    }
}
