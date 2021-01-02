package com.essenstore.utils;

import com.essenstore.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Utils {

    private static final ModelMapper mapper = new ModelMapper();

    private static final String sysTempDirectory = System.getProperty("java.io.tmpdir");

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

    public static File convertMultipartToFile(Image image) {
        File file = new File(sysTempDirectory + "/" + image.getName());
        try {
            image.getMultipartFile().transferTo(file);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return file;
    }

    public static Mail getMail(ActivationCode code) {
        return Mail.builder()
                .to(code.getUser().getEmail())
                .subject("Confirm code")
                .model(Map.of("code", code.getCode(),
                        "name", code.getUser().getName(),
                        "surname", code.getUser().getSurname(),
                        "link", code.getUrl()))
                .build();
    }

    public static ActivationCode generateCode(User user, String url) {
        var code = UUID.randomUUID().toString();
        return ActivationCode
                .builder()
                .code(code)
                .user(user)
                .url(format("%s/api/auth/%s", url, code))
                .build();
    }

    public static Duration timeDifferenceFromNow(Instant instant) {
        return Duration.between(instant, Instant.now());
    }
}
