package com.essenstore.entity;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@MappedSuperclass
@ToString(of = {"name"}, callSuper = true)
public abstract class BaseEntity extends AuditEntity implements Nameable {

    @NotBlank
    @Size(max = 250, min = 1)
    @Column(name = "name", unique = true)
    private String name;

    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }

}
