package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Order;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
@ApplicationScoped
@Named
public class OrderRepository {

    @Inject
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    private void init() {
        this.connection = dataSource.getConnection();
        createOrdersTable();
    }

    public boolean insert(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("insert into orders(product, cost, count) values (?,?,?);")) {
            statement.setString(1, order.getProduct());
            statement.setInt(2, order.getCost());
            statement.setInt(3, order.getCount());
            return statement.execute();
        } catch (SQLException e) {
            log.warn("Error when inserting a string into the orders", e);
            return false;
        }
    }

    public boolean delete(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("delete from orders where id = ?;")){
            statement.setInt(1, order.getId());
            return statement.execute();
        } catch (SQLException e) {
            log.warn("Error deleting a row from the orders", e);
            return false;
        }
    }

    public boolean createOrdersTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists orders (\n" +
                    "    id serial,\n" +
                    "    product varchar(255),\n" +
                    "    count integer,\n" +
                    "    cost integer\n" +
                    ");");
            return true;
        } catch (SQLException e) {
            log.warn("Error when creating a orders table in the database", e);
            return false;
        }
    }
}
