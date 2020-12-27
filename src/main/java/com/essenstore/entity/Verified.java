package com.essenstore.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Verified {
    ENABLED(true), DISABLED(false);

    private final boolean isVerified;

    }
