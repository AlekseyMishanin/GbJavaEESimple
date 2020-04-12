package ru.mishanin.controller;

import ru.mishanin.persist.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;
}
