package com.essenstore.entity;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Getter
public enum Gender {

    MAN("men"), WOMAN("women"), BOY("boys"), GIRL("girls"), EMPTY("empty");

    private final String path;

    Gender(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return StringUtils.capitalize(this.name());
    }

}
