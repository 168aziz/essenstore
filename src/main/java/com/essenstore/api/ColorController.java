package com.essenstore.api;

import com.essenstore.entity.Color;
import com.essenstore.service.ColorService;
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
@RequestMapping("api/color")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ColorController {

    private final ColorService colorService;

    @GetMapping(params = {"page"})
    public Page<Color> getAll(@PageableDefault Pageable pageable) {
        return colorService.getAll(pageable);
    }

    @GetMapping
    public List<Color> getAll() {
        return colorService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Color> get(@PathVariable Long id) {
        var color = colorService.getBy(id);
        if (color.isExist())
            return new ResponseEntity<>(color, OK);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Color> add(@RequestBody Color color) {
        var saved = colorService.save(color);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Color color) {
        var current = colorService.getBy(id);
        if (current.isExist()) {
            current.setName(color.getName());
            colorService.save(current);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        var color = colorService.getBy(id);
        if (color.isExist()) {
            colorService.delete(color);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

}
