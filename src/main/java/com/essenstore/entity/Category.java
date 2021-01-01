package com.essenstore.entity;

import com.essenstore.service.CategoryService;
import com.essenstore.validator.Unique;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "category")
@NamedEntityGraph(name = "Category.products",
        attributeNodes = @NamedAttributeNode("products"))
public class Category extends BaseEntity {

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final Set<Product> products = new HashSet<>();

    @Override
//    @Unique(service = CategoryService.class)
    public String getName() {
        return super.getName();
    }
}
