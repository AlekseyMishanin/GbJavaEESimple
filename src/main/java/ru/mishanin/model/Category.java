package ru.mishanin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Integer id;
    private String title;

    public Category(String title) {
        this.title = title;
    }
}
