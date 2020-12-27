package com.essenstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageDto {

    private int page;

    private int size;

    @JsonProperty("total-pages")
    private int totalPages;

    @JsonProperty("total-elements")
    private long totalElements;

    private List<?> content;

}
