package ru.mishanin.model;

import lombok.*;
import ru.mishanin.service.ProductCustom;

import javax.persistence.*;

@Table(name = "products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private Integer cost;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String title, Integer cost, Category category) {
        this.title = title;
        this.cost = cost;
        this.category = category;
    }

    public Product(ProductCustom productCustom) {
        this.title = productCustom.getTitle();
        this.cost = productCustom.getCost();
        this.category = productCustom.getCategory();
    }
}
