package com.essenstore.converter;

import com.essenstore.entity.Size;
import com.essenstore.repository.SizeRepository;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Setter(onMethod = @__(@Autowired))
public class SizeFormatter implements Formatter<Size> {

    private SizeRepository sizeRepository;

    @Override
    public Size parse(String size, Locale locale) {
        return StringUtils.isNumeric(size) ? sizeRepository.getOne(Long.parseLong(size)) : null;
    }

    @Override
    public String print(Size size, Locale locale) {
        return size.getName();
    }
}
