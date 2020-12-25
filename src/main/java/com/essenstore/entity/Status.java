package com.essenstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE(true), DELETE(false);

    private final boolean isActive;
}
