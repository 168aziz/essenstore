package com.essenstore.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brand")
@Accessors(chain = true)
@ToString(of = {"name"}, callSuper = true)
public class Brand extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Product> products = new HashSet<>();

}
