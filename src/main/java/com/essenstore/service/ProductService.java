package com.essenstore.service;

import com.essenstore.entity.Product;
import com.essenstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    @Value("${aws.s3.bucket.folder.product.image}")
    private String productFolderName;

    private final ProductRepository productRepository;

    private final AWSS3Service awss3Service;

    @Transactional
    public Product save(Product product) {
        var productPath = format("%s/%s/%s/%s", product.getGender(),
                product.getCategory().getName(), product.getBrand().getName(),
                product.getName()).replaceAll("\\s", "-");
        product.getImages()
                .stream()
                .map(img -> {
                    img.setPath(format("%s/%s/", productFolderName, productPath));
                    img.setProduct(product);
                    return img;
                }).forEach(awss3Service::upload);
        return productRepository.save(product);

    }

    @Transactional
    public Product update() {
        return null;
    }
}
