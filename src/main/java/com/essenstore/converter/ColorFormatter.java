package com.essenstore.converter;


import com.essenstore.entity.Color;
import com.essenstore.repository.ColorRepository;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Setter(onMethod = @__(@Autowired))
public class ColorFormatter implements Formatter<Color> {

    private ColorRepository colorRepository;

    @Override
    public Color parse(String color, Locale locale) {
        return StringUtils.isNumeric(color) ? colorRepository.getOne(Long.parseLong(color)) : null;
    }

    @Override
    public String print(Color color, Locale locale) {
        return color.getName();
    }
}
