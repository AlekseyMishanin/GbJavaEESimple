package ru.mishanin.service;

import javax.ejb.Local;
import java.util.List;

public interface ProductService {

    void insert(ProductCustom product);
    void update(ProductCustom product);
    void delete(int id);
    ProductCustom findById(int id);
    List<ProductCustom> findAll();
    List<ProductCustom> findAllByCategory(String categoryValue);
}
