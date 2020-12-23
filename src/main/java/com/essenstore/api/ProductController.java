package com.essenstore.api;

import com.essenstore.entity.Product;
import com.essenstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{gender}/{category}")
    public ResponseEntity<Page<Product>> getBy(@PathVariable String gender,
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

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        var currentProduct = productService.getBy(id);
        if (!currentProduct.isExist())
            return ResponseEntity.notFound().build();
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setBrand(product.getBrand());
        currentProduct.setOldPrice(product.getOldPrice());
        currentProduct.setCurrentPrice(product.getCurrentPrice());
        currentProduct.setGender(product.getGender());
        currentProduct.setColors(product.getColors());
        currentProduct.setSizes(product.getSizes());
        productService.save(currentProduct);
        return ResponseEntity.ok(currentProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var product = productService.getBy(id);
        if (!product.isExist())
            return ResponseEntity.notFound().build();
        productService.delete(product);
        return ResponseEntity.accepted().build();
    }

}
