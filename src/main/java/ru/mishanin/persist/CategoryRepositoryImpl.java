package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Category;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Stateless
@Log4j2
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @TransactionAttribute
    public void insert(Category category) {
        em.persist(category);
    }

    @TransactionAttribute
    public void update(Category category) {
        em.merge(category);
    }

    @TransactionAttribute
    public void delete(Category category) {

        Category categoryForDelete = em.find(Category.class, category.getId());
        if(categoryForDelete != null) {
            em.remove(categoryForDelete);
        }
    }

    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
