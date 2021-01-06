package com.essenstore.api;

import com.essenstore.converter.ProductConverter;
import com.essenstore.dto.ProductDto;
import com.essenstore.entity.Product;
import com.essenstore.repository.ProductRepository;
import com.essenstore.service.AWSS3Service;
import com.essenstore.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RepositoryRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductRepository repository;

    private final ProductConverter converter;

    private final AWSS3Service awss3Service;

    @Value("${image.url}")
    private String imageUrl;

    @RequestMapping(value = "/products", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestPart("product") ProductDto productDto, @RequestPart("images") List<MultipartFile> files) {
        return dtoToProduct(productDto, files)
                .map(product -> {
                    repository.save(product);
                    awss3Service.upload(product.getImages());
                    return ResponseEntity.accepted().build();
                }).orElse(ResponseEntity.badRequest().build());
    }

    private Optional<Product> dtoToProduct(ProductDto productDto, List<MultipartFile> files) {
        var product = converter.convert(productDto);
        if (product != null) {
            if (product.getImages() == null)
                product.setImages(new HashSet<>());
            product.getImages().addAll(Utils.multipartListToImageSet(files, imageUrl, Utils.buildPath(product)));
            product.getImages().forEach(image -> image.setProduct(product));
            return Optional.of(product);
        }
        return Optional.empty();
    }


}
