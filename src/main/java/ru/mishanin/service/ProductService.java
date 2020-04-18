package ru.mishanin.service;

import java.util.List;

public interface ProductService {

    void insert(ProductCustom product);
    void update(ProductCustom product);
    void delete(long id);
    ProductCustom findById(long id);
    List<ProductCustom> findAll();
    List<ProductCustom> findAllByCategory(String categoryValue);
}
