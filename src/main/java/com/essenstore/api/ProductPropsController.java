package com.essenstore.api;

import com.essenstore.dto.PageDto;
import com.essenstore.dto.PropDto;
import com.essenstore.entity.BaseEntity;
import com.essenstore.factory.EntityServiceFactory;
import com.essenstore.factory.EntityServiceName;
import com.essenstore.service.BaseEntityService;
import com.essenstore.utils.Utils;
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

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("api/{service}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductPropsController {

    private final EntityServiceFactory serviceFactory;

    private final ModelMapper mapper;

    @GetMapping(params = {"page"})
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName,
                                    @PageableDefault(size = 18) Pageable pageable) {
        var page = mapper.map(getService(serviceName).getAll(pageable), PageDto.class);
        page.setContent(Utils.mapList(page.getContent(), PropDto.class));
        return ResponseEntity.ok(page);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAll(@PathVariable("service") EntityServiceName serviceName) {
        return ResponseEntity.ok(Utils.mapList(getService(serviceName).getAll(), PropDto.class));
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> get(@PathVariable("service") EntityServiceName serviceName,
                                 @PathVariable @Positive Long id) {
        return ResponseEntity.ok(mapper.map(getService(serviceName).getBy(id), PropDto.class));
    }

    @PostMapping
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> add(@PathVariable("service") EntityServiceName serviceName,
                                 @RequestBody @Valid PropDto propDto) {
        var service = getService(serviceName);
        var saved = service.save(mapper.map(propDto, service.getEmptyObject().getClass()));
        return ResponseEntity.ok(mapper.map(saved, PropDto.class));

    }

    @PutMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> update(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable @Positive Long id,
                                    @RequestBody @Valid PropDto propDto) {
        var service = getService(serviceName);
        var result = service.update(id, mapper.map(propDto, service.getEmptyObject().getClass()));
        return ResponseEntity.ok(mapper.map(result, PropDto.class));
    }

    @DeleteMapping("{id}")
    @Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<?> remove(@PathVariable("service") EntityServiceName serviceName,
                                    @PathVariable @Positive Long id) {
        getService(serviceName).delete(id);
        return ResponseEntity.accepted().build();
    }

    private BaseEntityService<BaseEntity, Long> getService(EntityServiceName serviceName) {
        return serviceFactory.getEntityServiceBean(serviceName.getServiceName());
    }

}
