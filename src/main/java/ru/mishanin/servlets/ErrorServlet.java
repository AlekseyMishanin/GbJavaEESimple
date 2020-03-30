package ru.mishanin.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Error", urlPatterns = "/errors")
public class ErrorServlet extends AbstractServlet {

    private static final String EXC = "javax.servlet.error.exception";
    private static final String MSG = "javax.servlet.error.message";
    private static final String ST = "javax.servlet.error.status_code";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        PrintWriter printWriter = resp.getWriter();
        Exception exception = (Exception) req.getAttribute(EXC);
        Integer statusCode = (Integer)req.getAttribute(ST);
        String msg = (String)req.getAttribute(MSG);

        printWriter.println("<html>");
        printWriter.println("<body>");
        printWriter.println("<hr>");
        printWriter.println("<h1>Sorry, an error has occurred that has prevent the server from servicing your request.<h2>");
        printWriter.println("<font size=5>");
        printWriter.println("<table align=center>");
        printWriter.println("<tr bgcolor=lightgrey>");
        printWriter.println("<td><b>Status code:</b></td><td>" + statusCode + "</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr>");
        printWriter.println("<td><b>Type of exception:</b></td><td>" + (exception != null ? exception.getClass() : "") + "</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr bgcolor=lightgrey>");
        printWriter.println("<td><b>Message description:</b></td><td>" + msg + "</td>");
        servletContext.log("Exception occurred",exception);
        printWriter.println("</tr>");
        printWriter.println("</table>");
        printWriter.println("</font>");
        printWriter.println("<hr>");
        printWriter.println("<hr>");
        printWriter.println("<center><h1>Please try again...</h1></center>");
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
}
