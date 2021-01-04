package com.essenstore.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.essenstore.entity.Image;
import com.essenstore.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AWSS3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    @Async
    public void upload(Image image) {
        try {
            File temp = Utils.convertMultipartToFile(image);
            amazonS3.putObject(new PutObjectRequest(bucketName, image.getPath() + image.getName(), temp));
            Files.delete(temp.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void upload(Collection<Image> images) {
        images.forEach(image -> {
            var metadata = new ObjectMetadata();
            metadata.setContentLength(image.getMultipartFile().getSize());
            metadata.setContentType(image.getMultipartFile().getContentType());
            try {
                var objectRequest = new PutObjectRequest(bucketName, image.getPath() + image.getName(), image.getMultipartFile().getInputStream(),metadata);
                amazonS3.putObject(objectRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Async
    public void delete(Collection<Image> images) {
        images.forEach(image -> amazonS3.deleteObject(bucketName, image.getPath() + image.getName()));
    }

    @Async
    public void delete(Image image) {
        amazonS3.deleteObject(bucketName, image.getPath() + image.getName());
    }

}
