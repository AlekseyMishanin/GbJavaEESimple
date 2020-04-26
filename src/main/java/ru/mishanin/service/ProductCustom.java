package ru.mishanin.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mishanin.model.Category;
import ru.mishanin.model.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCustom {

    private Integer id;
    private String title;
    private Integer cost;
    private Category category;

    public ProductCustom(Product product) {
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.category = product.getCategory();
    }
}
