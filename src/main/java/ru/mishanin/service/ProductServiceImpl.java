package ru.mishanin.service;

import ru.mishanin.model.Product;
import ru.mishanin.persist.ProductRepository;
import ru.mishanin.persist.ProductRepositoryImpl;
import ru.mishanin.rest.ProductServiceRs;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Named
@WebService(endpointInterface = "ru.mishanin.service.ProductServiceWs", serviceName = "ProductService")
public class ProductServiceImpl implements ProductService, Serializable, ProductServiceWs, ProductServiceRs {

    @EJB
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
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public ProductCustom findById(int id) {
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
