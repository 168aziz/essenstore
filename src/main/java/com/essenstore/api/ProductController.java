package com.essenstore.api;

import com.essenstore.entity.Product;
import com.essenstore.repository.ProductRepository;
import com.essenstore.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
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

    private String imageUrl = "fwef";


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestPart("product") Product product, @RequestPart("images") List<MultipartFile> files) {
        product.setImages(Utils.multipartListToImageSet(files, imageUrl, Utils.buildPath(product)));

        repository.save(product);
        return ResponseEntity.accepted().build();
    }


}
