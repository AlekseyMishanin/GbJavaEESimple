package ru.mishanin.persist;

import lombok.extern.log4j.Log4j2;
import ru.mishanin.model.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Log4j2
@ApplicationScoped
@Named
public class CategoryRepository {

    @Inject
    private DataSource dataSource;

    private Connection connection;

    @PostConstruct
    private void init() {
        this.connection = dataSource.getConnection();
        createCategoryTable();

        IntStream.range(0,10)
                .mapToObj(index -> new Category(String.format("Category №%d", index)))
                .forEach(this::insert);

    }

    public boolean insert(Category category) {
        try (PreparedStatement statement = connection.prepareStatement("insert into category(title) values ( ? ) ;")) {
            statement.setString(1, category.getTitle());
            return statement.execute();
        } catch (SQLException e) {
            log.warn("Error when inserting a string into the category", e);
            return false;
        }
    }

    public boolean update(Category category) {
        try (PreparedStatement statement = connection.prepareStatement("update category set title = ? where id = ? ;")){
            statement.setString(1, category.getTitle());
            statement.setInt(2, category.getId());
            return statement.execute();
        } catch (SQLException e) {
            log.warn("Error when updating a string into the category", e);
            return false;
        }
    }

    public boolean delete(Category category) {
        try (PreparedStatement statement = connection.prepareStatement("delete from category where id = ? ;")){
            statement.setInt(1, category.getId());
            return statement.execute();
        } catch (SQLException e) {
            log.warn("Error deleting a row from the category", e);
            return false;
        }
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("select id, title from category");

            while (resultSet.next()) {
                res.add(new Category(resultSet.getInt(1), resultSet.getString(2)));
            }
        }
        return res;
    }

    private boolean createCategoryTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists category (\n" +
                    "    id serial,\n" +
                    "    title varchar(255)\n" +
                    ");");
            return true;
        } catch (SQLException e) {
            log.warn("Error when creating a category table in the database", e);
            return false;
        }
    }
}
