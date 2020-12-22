package com.essenstore.api;


import com.essenstore.entity.Category;
import com.essenstore.entity.Size;
import com.essenstore.service.SizeService;
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
@RequestMapping("api/size")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SizeController {

    private final SizeService sizeService;

    @GetMapping(params = {"page"})
    public Page<Size> getAll(@PageableDefault Pageable pageable) {
        return sizeService.getAll(pageable);
    }

    @GetMapping
    public List<Size> getAll() {
        return sizeService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Size> get(@PathVariable Long id) {
        var category = sizeService.getBy(id);
        if (category.isExist())
            return new ResponseEntity<>(category, OK);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Size> add(@RequestBody Size category) {
        var saved = sizeService.save(category);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
        var current = sizeService.getBy(id);
        if (current.isExist()) {
            current.setName(category.getName());
            sizeService.save(current);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        var size = sizeService.getBy(id);
        if (size.isExist()) {
            sizeService.delete(size);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

}

