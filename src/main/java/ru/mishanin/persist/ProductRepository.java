package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Category;
import ru.mishanin.model.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;

@ApplicationScoped
@Named
@Log4j2
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws SystemException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        if (findAll().size() == 0) {
            try {
                ut.begin();
                insert(new Product("Product1", 100, new Category(0, "Category №0")));
                insert(new Product("Product2", 200, new Category(2, "Category №2")));
                insert(new Product("Product3", 400, new Category(2, "Category №2")));
                insert(new Product("Product4", 500, new Category(3, "Category №3")));
            } catch (Exception e) {
                log.warn("An exception occurred when initializing the product table", e);
                ut.rollback();
            }
            ut.commit();
        }
    }

    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
        }
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    public List findAllByCategory(String categoryValue) {
        return em.createQuery("from Product p join p.category c WHERE c.title = :categoryName")
                .setParameter("categoryName", categoryValue)
                .getResultList();
    }
}
