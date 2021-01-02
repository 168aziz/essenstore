package com.essenstore.entity;

import lombok.Getter;
import lombok.Setter;

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
