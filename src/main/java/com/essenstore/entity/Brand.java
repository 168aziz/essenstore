package com.essenstore.entity;

import com.essenstore.service.BrandService;
import com.essenstore.validator.Unique;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brand")
@Accessors(chain = true)
@ToString(of = {"name"}, callSuper = true)
public class Brand extends BaseEntity {

    @JsonProperty("name")
    @Unique(service = BrandService.class)
    @NotBlank(message = "Name is not valid")
    @Size(min = 1, max = 150, message = "Name length`s must be between [1 200]")
    @Column(name = "name", unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Product> products = new HashSet<>();

}
