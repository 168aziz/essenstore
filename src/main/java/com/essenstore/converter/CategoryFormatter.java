package com.essenstore.converter;

import com.essenstore.entity.Category;
import com.essenstore.repository.CategoryRepository;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Setter(onMethod = @__(@Autowired))
public class CategoryFormatter implements Formatter<Category> {

    private CategoryRepository categoryRepository;

    @Override
    public Category parse(String category, Locale locale) {
        return StringUtils.isNumeric(category) ? categoryRepository.getOne(Long.parseLong(category)) : null;
    }

    @Override
    public String print(Category category, Locale locale) {
        return category.getName();
    }

}
