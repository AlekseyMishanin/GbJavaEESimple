package ru.mishanin.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Integer id;
    private String product;
    private Integer count;
    private Integer cost;

    public Order(String product, Integer count, Integer cost) {
        this.product = product;
        this.count = count;
        this.cost = cost;
    }
}
