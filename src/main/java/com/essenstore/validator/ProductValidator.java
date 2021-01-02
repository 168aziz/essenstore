package com.essenstore.validator;

import com.essenstore.entity.Color;
import com.essenstore.entity.Gender;
import com.essenstore.entity.Product;
import com.essenstore.entity.Size;
import com.essenstore.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductValidator implements Validator {

    private final ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Product.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Product product = (Product) obj;

        if (product.getGender() == Gender.EMPTY)
            errors.rejectValue("gender", HttpStatus.BAD_REQUEST.toString(), "Gender is not valid");

        if (product.getSizes().stream().anyMatch(Size::isNone))
            errors.rejectValue("sizes", HttpStatus.BAD_REQUEST.toString(), "Size is not valid");

        if (product.getColors().stream().anyMatch(Color::isNone))
            errors.rejectValue("colors", HttpStatus.BAD_REQUEST.toString(), "Colors is not valid");

//        var isImgs = product.getImages()
//                .stream()
//                .map(img -> FilenameUtils.getExtension(img.getName()))
//                .allMatch(ext -> ext.matches("^(png|PNG|jpg|JPG|jpeg|JPEG|webp|WEBP)$"));
//        if (!isImgs)
//            errors.rejectValue("images", "Images not supported", "Imgs not supported");
//
//        if (product.getImages().size() < 2)
//            errors.rejectValue("images", "Upload two images", "Upload two images");
//
//        if (product.getImages().stream().anyMatch(image -> image.getSize() > 5242880))
//            errors.rejectValue("images", "Image size too large");

        if (productRepository.existsByName(product.getName()))
            errors.rejectValue("name", HttpStatus.BAD_REQUEST.toString(), "Name is exists");


    }
}
