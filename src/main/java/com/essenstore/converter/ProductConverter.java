package com.essenstore.converter;

import com.essenstore.dto.ProductDto;
import com.essenstore.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductConverter implements Converter<ProductDto, Product> {

    private final UriToEntityConversionService conversionService;

    private final ModelMapper mapper;

    @Override
    public Product convert(ProductDto source) {
        var target = mapper.map(source, Product.class);

        if (source.getBrand() != null)
            target.setBrand(conversionService.convert(new Link(source.getBrand()), Brand.class));

        if (source.getCategory() != null)
            target.setCategory(conversionService.convert(new Link(source.getCategory()), Category.class));

        if (source.getSizes() != null) {
            var sizes = source.getSizes()
                    .stream()
                    .map(link -> conversionService.convert(new Link(link), Size.class))
                    .collect(Collectors.toSet());
            target.setSizes(sizes);
        }

        if (source.getColors() != null) {
            var colors = source.getColors()
                    .stream()
                    .map(link -> conversionService.convert(new Link(link), Color.class))
                    .collect(Collectors.toSet());
            target.setColors(colors);
        }

        if (source.getImages() != null) {
            var images = source.getImages()
                    .stream()
                    .map(link -> conversionService.convert(new Link(link), Image.class))
                    .collect(Collectors.toSet());
            target.setImages(images);
        }

        return target;
    }

}
