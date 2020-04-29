package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Category;
import ru.mishanin.model.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;

@Stateless
@Log4j2
public class ProductRepositoryImpl implements ProductRepository{

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    @TransactionAttribute
    public void insert(Product product) {
        em.persist(product);
    }
    @Override
    @TransactionAttribute
    public void update(Product product) {
        em.merge(product);
    }
    @Override
    @TransactionAttribute
    public void delete(int id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }
    @Override
    public Product findById(int id) {
        return em.find(Product.class, id);
    }
    @Override
    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }
    @Override
    public List<Product> findAllByCategory(String categoryValue) {
        return em.createQuery("select p from Product p join p.category c WHERE c.title = :categoryName")
                .setParameter("categoryName", categoryValue)
                .getResultList();
    }
}
