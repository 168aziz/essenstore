package com.essenstore.api;

import com.essenstore.dto.NameDto;
import com.essenstore.entity.BaseEntity;
import com.essenstore.exception.BadRequestException;
import com.essenstore.factory.EntityServiceFactory;
import com.essenstore.factory.EntityServiceName;
import com.essenstore.service.BaseEntityService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("api/{service}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductPropsController {

    private final EntityServiceFactory serviceFactory;

    @GetMapping(params = {"page"})
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName,
                                    @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(getService(serviceName).getAll(pageable));
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName) {
        return ResponseEntity.ok(getService(serviceName).getAll());
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> get(@PathVariable("service") EntityServiceName serviceName,
                                 @PathVariable @Positive Long id) {
        return ResponseEntity.ok(getService(serviceName).getBy(id));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<BaseEntity> add(@PathVariable("service") EntityServiceName serviceName,
                                          @RequestBody @Positive NameDto nameDto) {
        var service = getService(serviceName);
        var entity = service.getEmptyObject();
        entity.setName(nameDto.getName());
        var saved = service.save(entity);
        service.getEmptyObject().setName("");
        return ResponseEntity.ok(saved);

    }

    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> update(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable @Positive Long id, @RequestBody NameDto nameDto) {
        var service = getService(serviceName);
        var currentEntity = service.getBy(id);
        currentEntity.setName(nameDto.getName());
        service.save(currentEntity);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> remove(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable @Positive Long id) {
        getService(serviceName).delete(id);
        return ResponseEntity.accepted().build();
    }

    private BaseEntityService<BaseEntity, Long> getService(EntityServiceName serviceName) {
        if (!isServiceExists(serviceName))
            throw new BadRequestException();
        return serviceFactory.getEntityServiceBean(serviceName.getServiceName());
    }

    private boolean isServiceExists(EntityServiceName serviceName) {
        return serviceName != EntityServiceName.EMPTY;
    }

}
