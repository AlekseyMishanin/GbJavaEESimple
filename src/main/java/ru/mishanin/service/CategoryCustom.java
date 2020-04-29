package ru.mishanin.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mishanin.model.Category;
import ru.mishanin.model.Product;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCustom {

    private Integer id;
    private String title;
//    private List<Product> products;

    public CategoryCustom(Category category) {
        this.title = category.getTitle();
//        this.products = category.getProducts();
    }
}
