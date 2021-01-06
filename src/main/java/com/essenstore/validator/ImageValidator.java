package com.essenstore.validator;

import org.apache.tika.Tika;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.Set;

public class ImageValidator implements ConstraintValidator<Images, Set<com.essenstore.entity.Image>> {

    private final static long IMAGE_SIZE = 5242880;

    private final static String CONTENT_TYPE = "image";

    @Override
    public boolean isValid(Set<com.essenstore.entity.Image> images, ConstraintValidatorContext constraintValidatorContext) {
        return images
                .stream()
                .allMatch(image -> {
                    var file = image.getMultipartFile();
                    if (file == null)
                        return false;
                    try {
                        var tika = new Tika();
                        String mimeType = tika.detect(file.getBytes());
                        if (mimeType != null)
                            return mimeType.split("/")[0].equals(CONTENT_TYPE) && file.getSize() < IMAGE_SIZE;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return false;
                });
    }
}
