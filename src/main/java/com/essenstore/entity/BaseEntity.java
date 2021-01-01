package com.essenstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@ToString(of = {"id", "name"})
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Nameable, Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 250, min = 1)
    @Column(name = "name", unique = true)
    private String name;

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @JsonIgnore
    @Column(name = "created_at")
    private Instant createdAt;

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;

    @Version
    @JsonIgnore
    @Column(name = "version")
    private int version;

    @JsonIgnore
    public boolean isExist() {
        return id != null && id != 0;
    }

    @JsonIgnore
    public boolean isNone() {
        return id == null || id == 0;
    }

    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }

}
