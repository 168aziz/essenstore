package com.essenstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    USER,
    ADMIN,
    MODERATOR;


    @Override
    public String getAuthority() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
