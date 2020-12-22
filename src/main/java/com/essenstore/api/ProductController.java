package com.essenstore.api;

import com.essenstore.entity.Product;
import com.essenstore.service.ProductService;
import com.essenstore.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    private final ProductValidator productValidator;

    @GetMapping("/{gender}/{category}")
    public ResponseEntity<Page<Product>> categoryProducts(@PathVariable String gender,
                                                          @PathVariable String category,
                                                          @PageableDefault Pageable pageable) {
        var products = productService.getBy(category, gender, pageable);

        if (products.isEmpty())
            return new ResponseEntity<>(NOT_FOUND);
        return new ResponseEntity<>(products, OK);
    }

    @GetMapping({"/women/{category}/{id}", "/men/{category}/{id}",
            "/boys/{category}/{id}", "/girls/{category}/{id}"})
    public ResponseEntity<Product> productDetails(@PathVariable("id") Product product) {
        if (!product.isExist())
            return new ResponseEntity<>(NOT_FOUND);
        return new ResponseEntity<>(product, OK);
    }

//    @PostMapping("/products/add")
//    public String add(@Valid @RequestBody Product product) {
//
//        return service.validateAndSave(product, map, bindingResult, request.getServletPath()) ?
//                format("redirect: /admin/%s", PRODUCT.getPath()) : "admin/add-product";
//    }


}
