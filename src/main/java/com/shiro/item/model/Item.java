package com.shiro.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    private Product product;
    private Integer productQuantity;

    public Double getTotal() {
        return product.getPrice() * productQuantity.doubleValue();
    }
}
