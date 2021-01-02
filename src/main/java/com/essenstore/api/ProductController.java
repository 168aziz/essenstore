package com.essenstore.api;

import com.essenstore.entity.Product;
import com.essenstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;


    @PostMapping("/products")
    public ResponseEntity<?> save(@ModelAttribute @Valid Product product) {
        System.out.println(product.getImages());
        System.out.println(product);
        productService.save(product);
        return ResponseEntity.accepted().build();
    }

}
