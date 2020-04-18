package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Category;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

@Log4j2
@ApplicationScoped
@Named
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    private void init() {
//        IntStream.range(0,10)
//                .mapToObj(index -> new Category(String.format("Category №%d", index)))
//                .forEach(this::insert);
    }

    public void insert(Category category) {
        em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }

    public void delete(Category category) {
        em.remove(category);
    }

    public List<Category> findAll() throws SQLException {
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
