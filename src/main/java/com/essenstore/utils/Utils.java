package com.essenstore.utils;

import com.essenstore.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.io.FilenameUtils.getExtension;

public class Utils {

    private static final ModelMapper mapper = new ModelMapper();

    private static final String sysTempDirectory = System.getProperty("java.io.tmpdir");

    public static Product updateProduct(Product current, Product product) {
        if (!StringUtils.isBlank(product.getName()))
            current.setName(product.getName());
        if (!StringUtils.isBlank(product.getDescription()))
            current.setDescription(product.getDescription());
        if (product.getCategory() != null)
            current.setCategory(product.getCategory());
        if (product.getBrand() != null)
            current.setBrand(product.getBrand());
        if (product.getGender() != null && product.getGender() != Gender.EMPTY)
            current.setGender(product.getGender());
        if (product.getCurrentPrice() != null)
            current.setCurrentPrice(product.getCurrentPrice());
        current.setOldPrice(product.getOldPrice());
//        if (product.getColors() != null)
//            current.setColors(product.getColors());
//        current.setSizes(product.getSizes());
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

    public static Set<Image> multipartListToImageSet(List<MultipartFile> files, String imageUrl, String path) {
        return files.stream()
                .map(file -> {
                    var fileUUID = UUID.randomUUID().toString();
                    var image = new Image();
                    image.setMultipartFile(file);
                    image.setSize(file.getSize());
                    image.setName(format("%s.%s", fileUUID, getExtension(file.getOriginalFilename())));
                    image.setPath(path);
                    image.setUrl(imageUrl);
                    return image;
                }).collect(Collectors.toSet());
    }

    public static String buildPath(Product product) {
        return format("%s/%s/%s/%s", product.getGender(),
                product.getCategory() != null ? product.getCategory().getName() : "undefined",
                product.getBrand() != null ? product.getBrand().getName() : "undefined",
                product.getName()).replaceAll("\\s", "-");
    }


}
