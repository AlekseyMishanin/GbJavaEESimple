package ru.mishanin.persist;

import ru.mishanin.model.Order;

import javax.ejb.Local;

@Local
public interface OrderRepository {

    void insert(Order order);
    void delete(Order order);
}
