package com.essenstore.api;

import com.essenstore.dto.PageDto;
import com.essenstore.dto.ProductPageDto;
import com.essenstore.entity.Product;
import com.essenstore.exception.NotFoundException;
import com.essenstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

    private final ProductService productService;

    private final ModelMapper mapper;

    @PreAuthorize("permitAll()")
    @GetMapping("/{gender}/{category}")
    public ResponseEntity<?> getBy(@PathVariable String gender,
                                   @PathVariable String category,
                                   @PageableDefault(size = 18) Pageable pageable) {
        return ResponseEntity.ok(mapper.map(productService.getBy(category, gender, pageable), ProductPageDto.class));
    }

    @PreAuthorize("permitAll()")
    @GetMapping({"/women/{category}/{id}", "/men/{category}/{id}",
            "/boys/{category}/{id}", "/girls/{category}/{id}"})
    public ResponseEntity<Product> productDetails(@PathVariable("id") Product product) {
        return ResponseEntity.ok(product);
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getBy(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getBy(id));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> add(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> update(@PathVariable @Positive Long id, @RequestBody Product product) throws NotFoundException {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) throws NotFoundException {
        productService.delete(id);
        return ResponseEntity.accepted().build();
    }

}
