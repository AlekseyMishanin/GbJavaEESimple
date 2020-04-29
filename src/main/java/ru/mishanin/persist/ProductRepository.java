package ru.mishanin.persist;

import ru.mishanin.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductRepository {

    void insert(Product product);
    void update(Product product);
    void delete(int id);
    Product findById(int id);
    List<Product> findAll();
    List<Product> findAllByCategory(String categoryValue);
}
