package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
@ApplicationScoped
@Named
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public void insert(Order order) {
        em.persist(order);
    }

    public void delete(Order order) {
        em.merge(order);
    }
}
