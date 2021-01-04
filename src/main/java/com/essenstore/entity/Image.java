package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Table(name = "image")
@ToString(of = {"path"}, callSuper = true)
public class Image extends BaseEntity {

    @NotBlank
    @Column(name = "path")
    private String path;

    @NotBlank
    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Transient
    @JsonIgnore
    private transient MultipartFile multipartFile;

    @Positive
    @Column(name = "file_size")
    private long size;

}
