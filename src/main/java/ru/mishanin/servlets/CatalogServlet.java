package ru.mishanin.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mishanin.model.Product;
import ru.mishanin.utils.Urls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "Catalog", urlPatterns = Urls.CATALOG)
public class CatalogServlet extends AbstractServlet {

    Logger log = LogManager.getLogger(CatalogServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            products.add(new Product("product" + i, i));
        }

        req.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(req, resp);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        log.info("Catalog servlet was destroy");
    }
}
