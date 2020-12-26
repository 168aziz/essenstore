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

    @Column(name = "name", unique = true)
    private String name;

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
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

    public boolean isExist() {
        return id != null && id != 0;
    }

    public boolean isNone() {
        return id == null || id == 0;
    }

    public void setName(String name) {
        this.name = StringUtils.normalizeSpace(name);
    }
}
