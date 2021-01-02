package com.essenstore.service;

import com.essenstore.entity.Image;

public interface StorageService {

    void upload(Image image);

    void delete(Image image);

    void edit();

    void get();
}
