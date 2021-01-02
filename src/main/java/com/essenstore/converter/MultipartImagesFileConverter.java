package com.essenstore.converter;

import com.essenstore.entity.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.lang.String.format;
import static org.apache.commons.io.FilenameUtils.getExtension;

@Component
public class MultipartImagesFileConverter implements Converter<MultipartFile, Image> {
    @Override
    public Image convert(MultipartFile multipartFile) {
        var fileUUID = UUID.randomUUID().toString();
        var image = new Image();
        image.setMultipartFile(multipartFile);
        image.setSize(multipartFile.getSize());
        image.setName(format("%s.%s", fileUUID, getExtension(multipartFile.getOriginalFilename())));
        return image;
    }
}
