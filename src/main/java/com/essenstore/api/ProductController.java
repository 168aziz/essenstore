package com.essenstore.api;

import com.essenstore.entity.Product;
import com.essenstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

import static com.essenstore.utils.Utils.updateProduct;

@Validated
@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("permitAll()")
    @GetMapping("/{gender}/{category}")
    public ResponseEntity<Page<Product>> getBy(@PathVariable String gender,
                                               @PathVariable String category,
                                               @PageableDefault Pageable pageable) {
        var products = productService.getBy(category, gender, pageable);
        if (products.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(products);
    }

    @PreAuthorize("permitAll()")
    @GetMapping({"/women/{category}/{id}", "/men/{category}/{id}",
            "/boys/{category}/{id}", "/girls/{category}/{id}"})
    public ResponseEntity<Product> productDetails(@PathVariable("id") Product product) {
        if (!product.isExist())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> add(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> update(@PathVariable @Positive Long id, @RequestBody Product product) {
        var currentProduct = productService.getBy(id);
        if (!currentProduct.isExist())
            return ResponseEntity.notFound().build();
        updateProduct(currentProduct, product);
        productService.save(currentProduct);
        return ResponseEntity.ok(currentProduct);
    }

    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
        var product = productService.getBy(id);
        if (!product.isExist())
            return ResponseEntity.notFound().build();
        productService.delete(product);
        return ResponseEntity.accepted().build();
    }

}
