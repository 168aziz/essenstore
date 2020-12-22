package com.essenstore.api;

import com.essenstore.entity.Category;
import com.essenstore.service.CategoryService;
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
@RequestMapping("api/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(params = {"page"})
    public Page<Category> getAll(@PageableDefault Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> get(@PathVariable Long id) {
        var category = categoryService.getBy(id);
        if (category.isExist())
            return new ResponseEntity<>(category, OK);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Category> add(@RequestBody Category category) {
        var saved = categoryService.save(category);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category) {
        var current = categoryService.getBy(id);
        if (current.isExist()) {
            current.setName(category.getName());
            categoryService.save(current);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        var category = categoryService.getBy(id);
        if (category.isExist()) {
            categoryService.delete(category);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

}
