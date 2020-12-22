package com.essenstore.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "image")
@Accessors(chain = true)
@ToString(of = {"name", "path"}, callSuper = true)
public class Image extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn
    private Product product;

    @Transient
    private transient MultipartFile multipartFile;

    @Column(name = "file_size")
    private long size;

}
