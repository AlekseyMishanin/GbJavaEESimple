package ru.mishanin.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
@Named
public class DataSource {

    @Inject
    private ServletContext ctx;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        String jdbcConnection = ctx.getInitParameter("jdbcConnectionString");
        String user = ctx.getInitParameter("dbUsername");
        String pass = ctx.getInitParameter("dbPassword");
        this.connection = DriverManager.getConnection(jdbcConnection,user,pass);
    }

    public Connection getConnection() {
        return connection;
    }
}
