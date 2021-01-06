package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "brand")
@ToString(of = {}, callSuper = true)
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy.class)
public class Brand extends BaseEntity {

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final Set<Product> products = new HashSet<>();

}
