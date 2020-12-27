package com.essenstore.formatter;

import com.essenstore.exception.BadRequestException;
import com.essenstore.factory.EntityServiceName;
import org.springframework.format.Formatter;

import java.util.Locale;

public class EntityServiceNameFormatter implements Formatter<EntityServiceName> {

    @Override
    public EntityServiceName parse(String path, Locale locale) {
        var serviceName = EntityServiceName.getByPath(path);
        if (serviceName == EntityServiceName.EMPTY)
            throw new BadRequestException();

        return serviceName;
    }

    @Override
    public String print(EntityServiceName entityServiceName, Locale locale) {
        return entityServiceName.toString();
    }
}
