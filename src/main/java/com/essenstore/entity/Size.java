package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "size")
@Accessors(chain = true)
@ToString(of = {"name"}, callSuper = true)
public class Size extends BaseEntity {

    @JsonProperty("name")
    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_size", joinColumns = @JoinColumn(name = "size_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

}

