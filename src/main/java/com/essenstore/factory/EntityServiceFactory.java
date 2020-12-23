package com.essenstore.factory;

import com.essenstore.entity.BaseEntity;
import com.essenstore.service.BaseEntityService;

public interface EntityServiceFactory {

    <T extends BaseEntity> BaseEntityService<T, Long> getEntityServiceBean(String name);

}
