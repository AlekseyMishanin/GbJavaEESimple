package ru.mishanin.controller;

import ru.mishanin.model.Product;
import ru.mishanin.service.ProductCustom;
import ru.mishanin.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    private ProductCustom product;

    public ProductCustom getProduct() {
        return product;
    }

    public void setProduct(ProductCustom product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductCustom();
        return "/product.xhtml?faces-redirect=true";
    }

    public List<ProductCustom> getAllProduct() {
        return productService.findAll();
    }

    public String editProduct(ProductCustom product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        if (product.getId() == null) {
            productService.insert(product);
        } else {
            productService.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }
}
