package com.essenstore.api;

import com.essenstore.entity.Brand;
import com.essenstore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/brand")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BrandController {

    private final BrandService brandService;

    @GetMapping(params = {"page"})
    public Page<Brand> getAll(@PageableDefault Pageable pageable) {
        return brandService.getAll(pageable);
    }

    @GetMapping
    public List<Brand> getAll() {
        return brandService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Brand> get(@PathVariable Long id) {
        var brand = brandService.getBy(id);
        if (brand.isExist())
            return new ResponseEntity<>(brand, OK);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Brand> add(@RequestBody Brand brand) {
        var saved = brandService.save(brand);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Brand brand) {
        var current = brandService.getBy(id);
        if (current.isExist()) {
            current.setName(brand.getName());
            brandService.save(current);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        var brand = brandService.getBy(id);
        if (brand.isExist()) {
            brandService.delete(brand);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

}
