package ru.mishanin.service;

import ru.mishanin.model.Product;
import ru.mishanin.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Named
public class ProductServiceImpl implements ProductService, Serializable {

    @Inject
    private ProductRepository productRepository;

    @Override
    public void insert(ProductCustom product) {
        productRepository.insert(new Product(product));
    }

    @Override
    public void update(ProductCustom product) {
        productRepository.update(new Product(product));
    }

    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductCustom findById(long id) {
        return new ProductCustom(productRepository.findById(id));
    }

    @Override
    public List<ProductCustom> findAll() {
        return productRepository.findAll().stream()
                .map(ProductCustom::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCustom> findAllByCategory(String categoryValue) {
        return productRepository.findAllByCategory(categoryValue).stream()
                .map(ProductCustom::new)
                .collect(Collectors.toList());
    }
}
