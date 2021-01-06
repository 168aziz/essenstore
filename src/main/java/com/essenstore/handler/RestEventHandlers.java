package com.essenstore.handler;

import com.essenstore.entity.Image;
import com.essenstore.entity.Product;
import com.essenstore.service.AWSS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestEventHandlers {

    private final AWSS3Service awss3Service;

    @HandleAfterDelete
    public void handleAfterDelete(Product product) {
        awss3Service.delete(product.getImages());
    }

    @HandleAfterDelete
    public void handleAfterDelete(Image image) {
        awss3Service.delete(image);
    }


}
