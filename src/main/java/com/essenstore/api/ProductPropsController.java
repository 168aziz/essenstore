package com.essenstore.api;

import com.essenstore.dto.NameDto;
import com.essenstore.entity.BaseEntity;
import com.essenstore.factory.EntityServiceFactory;
import com.essenstore.factory.EntityServiceName;
import com.essenstore.service.BaseEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/{service}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductPropsController {

    private final EntityServiceFactory serviceFactory;

    @GetMapping(params = {"page"})
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName,
                                    @PageableDefault Pageable pageable) {
        return getService(serviceName)
                .map(service -> {
                    var page = service.getAll(pageable);
                    if (!page.hasContent())
                        return ResponseEntity.notFound().build();
                    return ResponseEntity.ok(page);
                }).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName) {
        return getService(serviceName)
                .map(service -> {
                    var list = service.getAll();
                    if (list.isEmpty())
                        return ResponseEntity.notFound().build();
                    return ResponseEntity.ok(list);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> get(@PathVariable("service") EntityServiceName serviceName,
                                 @PathVariable Long id) {
        return getService(serviceName)
                .map(service -> {
                    var entity = service.getBy(id);
                    if (!entity.isExist())
                        return ResponseEntity.notFound().build();
                    return ResponseEntity.ok(entity);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<BaseEntity> add(@PathVariable("service") EntityServiceName serviceName,
                                          @RequestBody NameDto nameDto) {
        return getService(serviceName)
                .map(service -> {
                    var entity = service.getEmptyObject();
                    entity.setName(nameDto.getName());
                    var saved = service.save(entity);
                    service.getEmptyObject().setName("");
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.badRequest().build());

    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable Long id, @RequestBody NameDto nameDto) {
        return getService(serviceName)
                .map(service -> {
                    var currentEntity = service.getBy(id);
                    if (!currentEntity.isExist())
                        return ResponseEntity.notFound().build();
                    currentEntity.setName(nameDto.getName());
                    service.save(currentEntity);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.badRequest().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable Long id) {
        return getService(serviceName)
                .map(service -> {
                    var entity = service.getBy(id);
                    if (!entity.isExist())
                        return ResponseEntity.notFound().build();
                    service.delete(entity);
                    return ResponseEntity.accepted().build();
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    private Optional<BaseEntityService<BaseEntity, Long>> getService(EntityServiceName serviceName) {
        if (!isServiceExists(serviceName))
            return Optional.empty();
        return Optional.of(serviceFactory.getEntityServiceBean(serviceName.getServiceName()));
    }

    private boolean isServiceExists(EntityServiceName serviceName) {
        return serviceName != EntityServiceName.EMPTY;
    }

}
