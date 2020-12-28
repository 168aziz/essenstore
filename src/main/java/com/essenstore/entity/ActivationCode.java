package com.essenstore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activation_code")
public class ActivationCode extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String code;
    @Transient
    private String url;

}
