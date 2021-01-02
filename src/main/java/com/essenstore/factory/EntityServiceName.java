package com.essenstore.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EntityServiceName {

    CATEGORY("category", "categoryService"),
    BRAND("brand", "brandService"),
    SIZE("size", "sizeService"),
    COLOR("color", "colorService"),
    PRODUCT("product", "productService"),
    EMPTY("", "");

    private final String path;

    private final String serviceName;

    @Override
    public String toString() {
        return serviceName;
    }

    public static EntityServiceName getByPath(String path) {
        return Arrays.stream(EntityServiceName.values())
                .filter(e -> e.path.equalsIgnoreCase(path))
                .findFirst()
                .orElse(EMPTY);
    }
}
