package com.essenstore.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brand")
@ToString(of = {}, callSuper = true)
public class Brand extends BaseEntity {

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final Set<Product> products = new HashSet<>();

}
