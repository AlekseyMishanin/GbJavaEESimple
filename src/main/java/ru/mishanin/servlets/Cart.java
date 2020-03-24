package ru.mishanin.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mishanin.utils.Urls;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Cart", urlPatterns = "/cart")
public class Cart extends AbstractServlet {

    Logger log = LogManager.getLogger(Cart.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<h1>" + this.getServletName() + "</h1>");
        resp.getWriter().println(
            "<ul>\n" +
            "  <li><a href="+ Urls.MAIN +">Main</a></li>\n" +
            "  <li><a href="+ Urls.CATALOG +">Catalog</a></li>\n" +
            "  <li><a href="+ Urls.CART +">Cart</a></li>\n" +
            "  <li><a href="+ Urls.PRODUCT +">Product</a></li>\n" +
            "  <li><a href="+ Urls.ORDER +">Order</a></li>\n" +
            "</ul>"
        );
    }

    @Override
    public void destroy() {
        log.info("Catalog servlet was destroy");
    }
}
