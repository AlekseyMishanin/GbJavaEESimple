package ru.mishanin.controller;

import ru.mishanin.model.Category;
import ru.mishanin.persist.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory() {
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategory() throws SQLException {
        return categoryRepository.findAll();
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public String saveCategory() {
        if (category.getId() == null) {
            categoryRepository.insert(category);
        } else {
            categoryRepository.update(category);
        }
        return "/categories.xhtml?faces-redirect=true";
    }
}
