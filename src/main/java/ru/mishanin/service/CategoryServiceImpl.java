package ru.mishanin.service;

import ru.mishanin.model.Category;
import ru.mishanin.persist.CategoryRepositoryImpl;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Named
public class CategoryServiceImpl implements CategoryService, Serializable {

    @Inject
    private CategoryRepositoryImpl categoryRepository;
    
    @Override
    public void insert(CategoryCustom category) {
        categoryRepository.insert(new Category(category));
    }

    @Override
    public void update(CategoryCustom category) {
        categoryRepository.update(new Category(category));
    }

    @Override
    public void delete(CategoryCustom category) {
        categoryRepository.delete(new Category(category));
    }

    @Override
    public List<CategoryCustom> findAll() {
        return categoryRepository.findAll().stream()
                .map((CategoryCustom::new))
                .collect(Collectors.toList());
    }
}
