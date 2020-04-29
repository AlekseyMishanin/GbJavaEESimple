package ru.mishanin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mishanin.service.CategoryCustom;

import javax.persistence.*;
import java.util.List;

@Table(name = "category")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Product> products;

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category(CategoryCustom categoryCustom) {
        this.id = categoryCustom.getId();
        this.title = categoryCustom.getTitle();
//        this.products = categoryCustom.getProducts();
    }
}
