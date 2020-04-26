package ru.mishanin.service;

import java.util.List;

public interface CategoryService {

    void insert(CategoryCustom category);
    void update(CategoryCustom category);
    void delete(CategoryCustom category);
    List<CategoryCustom> findAll();
}
