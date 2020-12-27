package com.essenstore.utils;

import com.essenstore.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final ModelMapper mapper = new ModelMapper();

    public static ResponseEntity<?> response(Object obj, HttpStatus httpStatus) {
        return new ResponseEntity<>(obj, httpStatus);
    }

    public static Product updateProduct(Product current, Product product) {
        current.setName(product.getName());
        current.setDescription(product.getDescription());
        current.setCategory(product.getCategory());
        current.setBrand(product.getBrand());
        current.setOldPrice(product.getOldPrice());
        current.setCurrentPrice(product.getCurrentPrice());
        current.setGender(product.getGender());
        current.setColors(product.getColors());
        current.setSizes(product.getSizes());
        return current;
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
