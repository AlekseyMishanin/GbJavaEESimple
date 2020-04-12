package ru.mishanin.persist;

import ru.mishanin.model.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    private Connection conn;

    @Inject
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException {
        this.conn = dataSource.getConnection();
        createTableIfNotExists(conn);

        if (findAll().size() == 0) {
            insert(new Product("Product1", 100));
            insert(new Product("Product2", 200));
            insert(new Product("Product3", 400));
            insert(new Product("Product4", 500));
        }
    }

    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products(title, cost) values (?, ?);")) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getCost());
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set title = ?, cost = ? where id = ?;")) {
            stmt.setString(1, product.getTitle());
            stmt.setInt(2, product.getCost());
            stmt.setLong(3, product.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title, cost from products where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
        }
        return new Product("", 0);
    }

    public List<Product> findAll() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title, cost from products");

            while (rs.next()) {
                res.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists products (\n" +
                    "    id serial,\n" +
                    "    title varchar(255),\n" +
                    "    cost decimal(19, 2) \n" +
                    ");");
        }
    }
}
