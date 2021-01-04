package com.essenstore.converter;

import com.essenstore.exception.BadRequestException;
import com.essenstore.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.repository.support.Repositories;
import org.springframework.data.repository.support.RepositoryInvokerFactory;
import org.springframework.data.rest.core.UriToEntityConverter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UriToEntityConversionService {

    private final MappingContext<?, ?> mappingContext;

    private final RepositoryInvokerFactory invokerFactory;

    private final Repositories repositories;

    public <T> T convert(Link link, Class<T> target) {

        PersistentEntities entities = new PersistentEntities(Collections.singletonList(mappingContext));
        UriToEntityConverter converter = new UriToEntityConverter(entities, invokerFactory, repositories);

        URI uri = convert(link);
        Object o = converter.convert(uri, TypeDescriptor.valueOf(URI.class), TypeDescriptor.valueOf(target));
        T object = target.cast(o);
        if (object == null) {
            throw new BadRequestException(String.format("%s '%s' was not found.", target.getSimpleName(), uri));
        }
        return object;
    }

    private URI convert(Link link) {
        try {
            return new URI(link.getHref());
        } catch (Exception e) {
            throw new IllegalArgumentException("URI from link is invalid", e);
        }
    }

}
