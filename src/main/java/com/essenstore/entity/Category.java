package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "category")
@ToString(of = "name", callSuper = true)
@NamedEntityGraph(name = "Category.products",
        attributeNodes = @NamedAttributeNode("products"))
public class Category extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Product> products = new HashSet<>();
}
