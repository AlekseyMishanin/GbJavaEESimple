package ru.mishanin.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mishanin.utils.Urls;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Product", urlPatterns = Urls.PRODUCT)
public class ProductServlet extends AbstractServlet {

    Logger log = LogManager.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contextPath = getServletContext().getContextPath();

        resp.getWriter().println("<h1>" + this.getServletName() + "</h1>");
        resp.getWriter().println(
                "<ul>\n" +
                        "  <li><a href="+ contextPath + Urls.MAIN +">Main</a></li>\n" +
                        "  <li><a href="+ contextPath + Urls.CATALOG +">Catalog</a></li>\n" +
                        "  <li><a href="+ contextPath + Urls.CART +">Cart</a></li>\n" +
                        "  <li><a href="+ contextPath + Urls.PRODUCT +">Product</a></li>\n" +
                        "  <li><a href="+ contextPath + Urls.ORDER +">Order</a></li>\n" +
                        "</ul>"
        );
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
