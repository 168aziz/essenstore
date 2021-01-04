package com.essenstore.api;

import com.essenstore.converter.ProductConverter;
import com.essenstore.dto.ProductDto;
import com.essenstore.repository.ProductRepository;
import com.essenstore.service.AWSS3Service;
import com.essenstore.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RepositoryRestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductRepository repository;

    private final ProductConverter converter;

    private final AWSS3Service awss3Service;

    private String imageUrl = "fwef";

    @RequestMapping(value = "/products", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> save(@RequestPart("product") ProductDto productDto, @RequestPart("images") List<MultipartFile> files) {
        var product = converter.convert(productDto);
        if (product != null) {
            product.setImages(Utils.multipartListToImageSet(files, imageUrl, Utils.buildPath(product)));
            product.getImages().forEach(image -> image.setProduct(product));
            repository.save(product);
            awss3Service.upload(product.getImages());
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
