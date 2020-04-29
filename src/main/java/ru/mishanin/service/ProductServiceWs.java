package ru.mishanin.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {

    @WebMethod
    ProductCustom findById(int id);
    @WebMethod
    List<ProductCustom> findAll();
}
