package com.essenstore.dto;

import com.essenstore.utils.Utils;

import java.util.List;

public class ProductPageDto extends PageDto {

    @Override
    public void setContent(List<?> content) {
        super.setContent(Utils.mapList(content, ProductDto.class));
    }
}
