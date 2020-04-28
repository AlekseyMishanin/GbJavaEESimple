package ru.mishanin.persist;

import ru.mishanin.model.Category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryRepository {

    void insert(Category category);
    void update(Category category);
    void delete(Category category);
    List<Category> findAll();
}
