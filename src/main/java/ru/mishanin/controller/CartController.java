package ru.mishanin.controller;

import ru.mishanin.model.Order;
import ru.mishanin.model.Product;
import ru.mishanin.persist.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SessionScoped
@Named
public class CartController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private final Map<Product, Integer> cart = new HashMap<>();

    public String openCart() {
        return "/cart.xhtml?faces-redirect=true";
    }

    public Set<Product> getAllProducts () {
        return cart.keySet();
    }

    public int getProductCount (Product product) {
        return cart.get(product);
    }

    public int getProductCost (Product product) {
        return cart.get(product) * product.getCost();
    }

    public void minusOneProduct (Product product) {
        int count = cart.get(product);
        --count;
        if(count <= 0) {
            cart.remove(product);
        } else {
            cart.put(product, count);
        }
    }

    public void plusOneProduct (Product product) {
        int count = cart.get(product);
        ++count;
        cart.put(product, count);
    }

    public synchronized void addToCart(Product product) {
        Integer countProducts = cart.get(product);

        if (countProducts != null && countProducts > 0) {
            ++countProducts;
        } else {
            countProducts = 1;
        }

        cart.put(product, countProducts);
    }

    public synchronized String saveOrder(){
        cart.entrySet().stream()
                .map((item) -> { return Order.builder()
                            .product(item.getKey().getTitle())
                            .count(item.getValue())
                            .cost(item.getKey().getCost() * item.getValue())
                            .build();
                })
                .forEach(orderRepository::insert);
        cart.clear();
        return "/index.xhtml?faces-redirect=true";
    }

    public synchronized void removeAllProduct(Product product) {
        cart.remove(product);
    }
}
