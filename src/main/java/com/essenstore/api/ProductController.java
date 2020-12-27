package com.essenstore.api;

import com.essenstore.dto.ProductDetailDto;
import com.essenstore.dto.ProductDto;
import com.essenstore.dto.ProductPageDto;
import com.essenstore.entity.Product;
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

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getBy(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.map(productService.getBy(id), ProductDto.class));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> add(@RequestBody ProductDetailDto productDto) {
        productService.save(mapper.map(productDto, Product.class));
        return ResponseEntity.accepted().build();
    }

    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> update(@PathVariable @Positive Long id,
                                    @RequestBody ProductDetailDto productDto) {
        return ResponseEntity.ok(productService.update(id, mapper.map(productDto, Product.class)));
    }

    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> delete(@PathVariable @Positive Long id) {
        productService.delete(id);
        return ResponseEntity.accepted().build();
    }

}
